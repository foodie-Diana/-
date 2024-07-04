<template>
  <div>
    <div id="EChartsComponent" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import { getCollegeYearlyAvgSalaries } from "@/api/employment/employment_analysis";
import * as echarts from 'echarts'; // 确保导入 ECharts

export default {
  name: 'EChartsComponent',
  // 在组件挂载到 DOM 后立即执行
  async mounted() {
    console.log("EChartsComponent mounted");
    // 调用初始化图表的异步方法
    await this.initChart();
  },
  methods: {
    async initChart() {
      console.log("Initializing chart...");

      // 获取 DOM 元素来渲染图表
      const chartElement = document.getElementById('main');
      if (!chartElement) {
        console.error("Chart element not found!");
        return;
      }

      // 如果 myChart 已经初始化，先销毁它以避免重复初始化
      if (this.myChart) {
        this.myChart.dispose();
      }

      // 初始化 ECharts 实例
      this.myChart = echarts.init(chartElement);

      console.log("Fetching data...");
      // 调用后端 API 获取数据
      const response = await getCollegeYearlyAvgSalaries();
      console.log("Data fetched: ", response);

      // 检查 API 调用是否成功
      if (response.code !== 200) {
        console.error("Failed to fetch data: ", response.message);
        return;
      }

      // 提取返回的数据
      const rawData = response.data;
      console.log("Raw data: ", rawData);

      // 将后端数据转换为 ECharts 需要的格式
      const formattedData = rawData.map(item => [
        item.avg_salary,  // 索引 0
        item.year,        // 索引 1
        null,             // 保留空位 2
        item.college_name // 索引 3
      ]);

      // 获取所有的院校名称
      const colleges = Array.from(new Set(rawData.map(item => item.college_name)));
      console.log("Colleges: ", colleges);

      const datasetWithFilters = [];
      const seriesList = [];

      // 为每个院校生成对应的数据集和系列
      colleges.forEach(college => {
        const datasetId = 'dataset_' + college;
        datasetWithFilters.push({
          id: datasetId,
          fromDatasetId: 'dataset_raw',
          transform: {
            type: 'filter',
            config: {
              and: [
                { dimension: 1, gte: 2000 }, // 过滤年份大于等于 2000 的数据
                { dimension: 3, '=': college } // 过滤院校名称为当前院校的数据
              ]
            }
          }
        });
        seriesList.push({
          type: 'line', // 线图
          datasetId: datasetId,
          showSymbol: false,
          name: college,
          endLabel: {
            show: true,
            formatter: function (params) {
              // 格式化最终标签
              return params.value[3] + ': ' + params.value[0];
            }
          },
          labelLayout: {
            moveOverlap: 'shiftY'
          },
          emphasis: {
            focus: 'series'
          },
          encode: {
            x: 1, // year
            y: 0, // avg_salary
            label: [3, 0], // [college_name, avg_salary]
            itemName: 1, // year
            tooltip: [0] // avg_salary
          }
        });
      });

      console.log("Series list: ", seriesList);

      // 定义 ECharts 配置项
      const option = {
        animationDuration: 10000,
        dataset: [
          {
            id: 'dataset_raw',
            source: formattedData
          },
          ...datasetWithFilters
        ],
        title: {
          text: '各院校历年平均薪资'
        },
        tooltip: {
          order: 'valueDesc',
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          nameLocation: 'middle'
        },
        yAxis: {
          name: '平均薪资'
        },
        grid: {
          right: 140
        },
        series: seriesList
      };

      console.log("ECharts option: ", option);

      // 设置 ECharts 配置项以生成图表
      this.myChart.setOption(option);
    }
  },
  beforeDestroy() {
    // 在组件销毁前销毁图表实例
    if (this.myChart) {
      this.myChart.dispose();
    }
  }
};
</script>
