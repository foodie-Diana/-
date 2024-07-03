<template>
  <div>
    <div id="popularMajorsChart" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import { getPopularMajors } from "@/api/employment/employment_analysis";
import * as echarts from 'echarts'; // 确保导入 ECharts

export default {
  name: 'PopularMajorsChart',
  async mounted() {
    console.log("PopularMajorsChart mounted");
    await this.initializeChart();
  },
  methods: {
    async initializeChart() {
      console.log("Initializing chart...");

      const chartElement = document.getElementById('popularMajorsChart');
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
      const response = await getPopularMajors();
      console.log("Data fetched: ", response);

      if (response.code !== 200) {
        console.error("Failed to fetch data: ", response.message);
        return;
      }

      const rawData = response.data;
      console.log("Raw data: ", rawData);

      const dimensions = ['major_name', 'major_count'];
      const dataset = {
        dimensions: dimensions,
        source: rawData
      };

      const option = {
        dataset: [
          dataset,
          {
            transform: {
              type: 'sort',
              config: { dimension: 'major_count', order: 'desc' }
            }
          }
        ],
        xAxis: {
          type: 'category',
          axisLabel: { interval: 0, rotate: 30 }
        },
        yAxis: {},
        series: {
          type: 'bar',
          encode: { x: 'major_name', y: 'major_count' },
          datasetIndex: 1
        }
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
