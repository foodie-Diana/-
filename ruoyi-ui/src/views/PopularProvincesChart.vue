<template>
  <div>
    <div id="popular-provinces-chart" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import { getPopularProvinces } from "@/api/employment/employment_analysis";
import * as echarts from 'echarts';

export default {
  name: 'PopularProvincesChart',
  async mounted() {
    console.log("PopularProvincesChart mounted");
    await this.initChart();
  },
  methods: {
    async initChart() {
      console.log("Initializing chart...");

      const chartElement = document.getElementById('popular-provinces-chart');
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
      const response = await getPopularProvinces();
      console.log("Data fetched: ", response);

      if (response.code !== 200) {
        console.error("Failed to fetch data: ", response.message);
        return;
      }

      let rawData = response.data;
      console.log("Raw data: ", rawData);

      // 仅取前6个省份及其就业人数

      const provinces = rawData.map(item => item.location);
      const counts = rawData.map(item => item.count);

      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {},
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value'
        },
        yAxis: {
          type: 'category',
          data: provinces,
          name: '省份'
        },
        series: [
          {
            name: '就业人数',
            type: 'bar',
            stack: 'total',
            label: {
              show: true
            },
            emphasis: {
              focus: 'series'
            },
            data: counts
          }
        ]
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
