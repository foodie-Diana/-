<template>
  <div>
    <div id="main" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import { getCollegeYearlyAvgSalaries } from "@/api/employment/employment_analysis";
import * as echarts from 'echarts'; // 确保导入 ECharts

export default {
  name: 'EChartsComponent',
  async mounted() {
    console.log("EChartsComponent mounted");
    await this.initChart();
  },
  methods: {
    async initChart() {
      console.log("Initializing chart...");

      const chartElement = document.getElementById('main');
      if (!chartElement) {
        console.error("Chart element not found!");
        return;
      }

      // 如果 myChart 已经初始化，先销毁它
      if (this.myChart) {
        this.myChart.dispose();
      }

      this.myChart = echarts.init(chartElement);

      console.log("Fetching data...");
      const response = await getCollegeYearlyAvgSalaries();
      console.log("Data fetched: ", response);

      if (response.code !== 200) {
        console.error("Failed to fetch data: ", response.message);
        return;
      }

      const rawData = response.data;
      console.log("Raw data: ", rawData);

      // 将后端数据转换为 ECharts 需要的格式
      const formattedData = rawData.map(item => [
        item.avg_salary,  // 索引 0
        item.year,        // 索引 1
        null,             // 保留空位 2
        item.college_name // 索引 3
      ]);

      const colleges = Array.from(new Set(rawData.map(item => item.college_name)));
      console.log("Colleges: ", colleges);

      const datasetWithFilters = [];
      const seriesList = [];

      colleges.forEach(college => {
        const datasetId = 'dataset_' + college;
        datasetWithFilters.push({
          id: datasetId,
          fromDatasetId: 'dataset_raw',
          transform: {
            type: 'filter',
            config: {
              and: [
                { dimension: 1, gte: 2000 },
                { dimension: 3, '=': college }
              ]
            }
          }
        });
        seriesList.push({
          type: 'line',
          datasetId: datasetId,
          showSymbol: false,
          name: college,
          endLabel: {
            show: true,
            formatter: function (params) {
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

      this.myChart.setOption(option);
    }
  },
  beforeDestroy() {
    // 销毁图表实例
    if (this.myChart) {
      this.myChart.dispose();
    }
  }
};
</script>
