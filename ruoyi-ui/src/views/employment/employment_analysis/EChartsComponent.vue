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
                { dimension: 'year', gte: 1950 },
                { dimension: 'college_name', '=': college }
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
              console.log('Params: ', params); // 打印参数内容
              if (params.value && typeof params.value === 'object') {
                return `${params.value.college_name}: ${params.value.avg_salary}`;
              } else {
                return 'undefined: undefined';
              }
            }
          },
          labelLayout: {
            moveOverlap: 'shiftY'
          },
          emphasis: {
            focus: 'series'
          },
          encode: {
            x: 'year',
            y: 'avg_salary',
            label: ['college_name', 'avg_salary'],
            itemName: 'year',
            tooltip: ['avg_salary']
          }
        });
      });

      console.log("Series list: ", seriesList);

      const option = {
        animationDuration: 10000,
        dataset: [
          {
            id: 'dataset_raw',
            source: rawData
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
