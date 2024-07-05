<template>
  <div>
    <div id="gender-pie-chart" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script>
import { getGender } from "@/api/menu"; // 假设接口文件路径为 @/api
import * as echarts from 'echarts';

export default {
  name: 'GenderPieChart',
  async mounted() {
    console.log("GenderPieChart mounted");
    await this.initChart();
  },
  methods: {
    async initChart() {
      console.log("Initializing chart...");

      const chartElement = document.getElementById('gender-pie-chart');
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
      const response = await getGender();
      console.log("Data fetched: ", response);

      if (response.code !== 200) {
        console.error("Failed to fetch data: ", response.message);
        return;
      }

      const rawData = response.data;
      console.log("Raw data: ", rawData);

      const data = rawData.map(item => ({
        value: item.count,
        name: item.gender === 'M' ? 'Male' : 'Female'
      }));

      const option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '6%',
          left: 'center'
        },
        series: [
          {
            name: 'Gender Distribution',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            title: {
              text: '各院校历年平均薪资'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: data
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
