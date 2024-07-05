<template>
  <div class="content">
    <div ref="charts" class="chart-container"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import china from './china.json';
import { getPopularProvinces } from '@/api/menu'; // 假设你的接口文件路径为 @/api

export default {
  data() {
    return {
      points: [],
      linesData: [],
      mapData: [],
      planePath: 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z'
    };
  },
  mounted() {
    this.fetchDataAndInitCharts();
  },
  methods: {
    async fetchDataAndInitCharts() {
      try {
        const response = await getPopularProvinces();
        const data = response.data;

        const validProvinces = this.getCoordinates();

        // 过滤掉无效的省份数据
        const validData = data.filter(item => validProvinces[item.location]);

        this.mapData = validData.map(item => ({
          value: item.count,
          name: item.location
        }));

        this.points = validData.map(item => ({
          name: item.location,
          value: validProvinces[item.location],
          itemStyle: { color: '#007BFF' }
        }));

        this.generateRandomLines();
        this.initCharts();
      } catch (error) {
        console.error('Failed to fetch data:', error);
      }
    },
    getCoordinates() {
      return {
        '北京': [116.407387, 39.904179],
        '天津': [117.199371, 39.0851],
        '河北': [114.502461, 38.045474],
        '山西': [112.578781, 37.813948],
        '内蒙古': [111.670801, 40.818311],
        '辽宁': [123.429096, 41.796767],
        '吉林': [125.3245, 43.886841],
        '黑龙江': [126.642464, 45.756967],
        '上海': [121.473701, 31.230416],
        '江苏': [118.763563, 32.061377],
        '浙江': [120.152575, 30.266619],
        '安徽': [117.283042, 31.86119],
        '福建': [119.306239, 26.075302],
        '江西': [115.892151, 28.676493],
        '山东': [117.020725, 36.670201],
        '河南': [113.753094, 34.767052],
        '湖北': [114.298572, 30.584355],
        '湖南': [112.982951, 28.116007],
        '广东': [113.266887, 23.133306],
        '广西': [108.327537, 22.816659],
        '海南': [110.33119, 20.031971],
        '重庆': [106.504962, 29.533155],
        '四川': [104.065735, 30.659462],
        '贵州': [106.713478, 26.578343],
        '云南': [102.712251, 25.040609],
        '西藏': [91.132212, 29.660361],
        '陕西': [108.953939, 34.266611],
        '甘肃': [103.826777, 36.060634],
        '青海': [101.778916, 36.623178],
        '宁夏': [106.278179, 38.46637],
        '新疆': [87.628579, 43.793301]
      };
    },
    generateRandomLines() {
      const points = this.points.map(point => point.value);
      const lines = [];

      for (let i = 0; i < points.length; i++) {
        const from = points[i];
        let to;
        do {
          to = points[Math.floor(Math.random() * points.length)];
        } while (from === to);

        lines.push({ coords: [from, to] });
      }

      this.linesData = lines;
    },
    initCharts() {
      const charts = echarts.init(this.$refs['charts']);
      const option = {
        backgroundColor: '#ffffff', // 浅蓝色背景颜色
        tooltip: {
          trigger: 'item', // 触发类型
          formatter: function (params) {
            if (params.seriesType === 'map') {
              // 只在地图区域显示人数
              if (params.data && params.data.value) {
                return `${params.name}: ${params.data.value} 人`;
              } else {
                return `${params.name}`;
              }
            } else if (params.seriesType === 'effectScatter') {
              // 散点图部分的提示信息
              return `${params.name}: ${params.value[0]}, ${params.value[1]}`;
            } else {
              // 线条部分的提示信息
              return `${params.name}`;
            }
          }
        },
        visualMap: {
          left: '20%',   // 将 left 属性调整为百分比或具体数值
          bottom: '20%', // 将 bottom 属性调整为百分比或具体数值
          min: 0,
          max: Math.max(...this.mapData.map(item => item.value)),
          text: ['High', 'Low'],
          realtime: false,
          calculable: true,
          inRange: {
            color: ['lightskyblue', 'yellow', 'orangered']
          }
        },
        geo: { // 地图配置
          map: 'china',
          label: { // 图形上的文本标签
            normal: { // 通常状态下的样式
              show: true,
              textStyle: {
                color: '#000000'
              }
            },
            emphasis: { // 鼠标放上去高亮的样式
              textStyle: {
                color: '#ffffff'
              }
            }
          },
          itemStyle: { // 地图区域的样式设置
            normal: { // 通常状态下的样式
              borderColor: '#1E90FF',
              borderWidth: 1,
              areaColor: { // 地图区域的颜色
               /* type: 'radial', // 径向渐变
                x: 0.5, // 圆心
                y: 0.5, // 圆心
                r: 0.8, // 半径 */
                colorStops: [
                  { // 0% 处的颜色
                    offset: 0,
                    color: 'rgba(30, 144, 255, 0)'
                  },
                  { // 100% 处的颜色
                    offset: 1,
                    color: 'rgba(30, 144, 255, .4)'
                  }
                ]
              }
            },
            // 鼠标放上去高亮的样式
            emphasis: {
              areaColor: '#1E90FF',
              borderWidth: 0
            }
          }
        },
        series: [
          { // 地图数据
            type: 'map',
            map: 'china',
            geoIndex: 0,
            data: this.mapData
          },
         /* { // 散点系列数据
            type: 'effectScatter', // 带有涟漪特效动画的散点（气泡）图
            coordinateSystem: 'geo', // 该系列使用的坐标系:地理坐标系
            symbolSize: 12, // 散点大小
            // 特效类型,目前只支持涟漪特效'ripple'，意为“涟漪”
            effectType: 'ripple',
            // 配置何时显示特效。可选'render'和'emphasis' 。
            showEffectOn: 'render',
            rippleEffect: { // 涟漪特效相关配置。
              period: 10, // 动画的周期，秒数。
              scale: 4, // 动画中波纹的最大缩放比例。
              // 波纹的绘制方式，可选 'stroke' 和 'fill'。
              brushType: 'fill'
            },
            zlevel: 1, // 所有图形的 zlevel 值。
            data: this.points
          }, */
          { // 线条系列数据
            type: 'lines',
            zlevel: 2,
            symbol: ['none', 'arrow'], // 标记的图形: 箭头
            symbolSize: 10, // 标记的大小
            effect: { // 线条特效的配置
              show: true,
              period: 6, // 特效动画的时间，单位s
              trailLength: 0, // 特效尾迹的长度。取值[0,1]值越大，尾迹越重
              symbol: this.planePath, // 特效图形的标记 可选'circle'等
              symbolSize: 15 // 特效标记的大小
            },
            lineStyle: { // 线条样式
              normal: {
                color: '#1E90FF',
                width: 2.5, // 线条宽度
                opacity: 0.6, // 尾迹线条透明度
                curveness: 0.2 // 尾迹线条曲直度
              }
            },
            data: this.linesData
          }
        ]
      };

      // 地图注册，第一个参数的名字必须和option.geo.map一致
      echarts.registerMap('china', china);
      charts.setOption(option);
    }
  }
};
</script>

<style scoped>
.content {
  background-color: #ffffff; /* 浅蓝色背景 */
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.chart-container {
  width: 100%;
  height: 100vh; /* 高度设置为90%视口高度 */
}
</style>
