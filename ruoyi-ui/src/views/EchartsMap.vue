<template>
  <div class="content">
    <div ref="charts" class="chart-container"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import china from './china.json'

export default {
  data () {
    return {
      // 散点图数据
      points: [
        { name: '北京', value: [116.407387, 39.904179], itemStyle: { color: '#FF5733' } },
        { name: '天津', value: [117.199371, 39.0851], itemStyle: { color: '#007BFF' } },
        { name: '河北', value: [114.502461, 38.045474], itemStyle: { color: '#007BFF' } },
        { name: '山西', value: [112.578781, 37.813948], itemStyle: { color: '#007BFF' } },
        { name: '内蒙古', value: [111.670801, 40.818311], itemStyle: { color: '#007BFF' } },
        { name: '辽宁', value: [123.429096, 41.796767], itemStyle: { color: '#007BFF' } },
        { name: '吉林', value: [125.3245, 43.886841], itemStyle: { color: '#007BFF' } },
        { name: '黑龙江', value: [126.642464, 45.756967], itemStyle: { color: '#007BFF' } },
        { name: '上海', value: [121.473701, 31.230416], itemStyle: { color: '#007BFF' } },
        { name: '江苏', value: [118.763563, 32.061377], itemStyle: { color: '#007BFF' } },
        { name: '浙江', value: [120.152575, 30.266619], itemStyle: { color: '#007BFF' } },
        { name: '安徽', value: [117.283042, 31.86119], itemStyle: { color: '#007BFF' } },
        { name: '福建', value: [119.306239, 26.075302], itemStyle: { color: '#007BFF' } },
        { name: '江西', value: [115.892151, 28.676493], itemStyle: { color: '#007BFF' } },
        { name: '山东', value: [117.020725, 36.670201], itemStyle: { color: '#007BFF' } },
        { name: '河南', value: [113.753094, 34.767052], itemStyle: { color: '#007BFF' } },
        { name: '湖北', value: [114.298572, 30.584355], itemStyle: { color: '#007BFF' } },
        { name: '湖南', value: [112.982951, 28.116007], itemStyle: { color: '#007BFF' } },
        { name: '广东', value: [113.266887, 23.133306], itemStyle: { color: '#007BFF' } },
        { name: '广西', value: [108.327537, 22.816659], itemStyle: { color: '#007BFF' } },
        { name: '海南', value: [110.33119, 20.031971], itemStyle: { color: '#007BFF' } },
        { name: '重庆', value: [106.504962, 29.533155], itemStyle: { color: '#007BFF' } },
        { name: '四川', value: [104.065735, 30.659462], itemStyle: { color: '#007BFF' } },
        { name: '贵州', value: [106.713478, 26.578343], itemStyle: { color: '#007BFF' } },
        { name: '云南', value: [102.712251, 25.040609], itemStyle: { color: '#007BFF' } },
        { name: '西藏', value: [91.132212, 29.660361], itemStyle: { color: '#007BFF' } },
        { name: '陕西', value: [108.953939, 34.266611], itemStyle: { color: '#007BFF' } },
        { name: '甘肃', value: [103.826777, 36.060634], itemStyle: { color: '#007BFF' } },
        { name: '青海', value: [101.778916, 36.623178], itemStyle: { color: '#007BFF' } },
        { name: '宁夏', value: [106.278179, 38.46637], itemStyle: { color: '#007BFF' } },
        { name: '新疆', value: [87.628579, 43.793301], itemStyle: { color: '#007BFF' } }
      ],
      linesData: [],
      mapData: [
        { name: '北京', value: 500 },
        { name: '天津', value: 200 },
        { name: '河北', value: 300 },
        { name: '山西', value: 150 },
        { name: '内蒙古', value: 100 },
        { name: '辽宁', value: 200 },
        { name: '吉林', value: 150 },
        { name: '黑龙江', value: 200 },
        { name: '上海', value: 300 },
        { name: '江苏', value: 310 },
        { name: '浙江', value: 290 },
        { name: '安徽', value: 170 },
        { name: '福建', value: 220 },
        { name: '江西', value: 180 },
        { name: '山东', value: 300 },
        { name: '河南', value: 260 },
        { name: '湖北', value: 240 },
        { name: '湖南', value: 210 },
        { name: '广东', value: 450 },
        { name: '广西', value: 180 },
        { name: '海南', value: 120 },
        { name: '重庆', value: 230 },
        { name: '四川', value: 200 },
        { name: '贵州', value: 150 },
        { name: '云南', value: 120 },
        { name: '西藏', value: 80 },
        { name: '陕西', value: 240 },
        { name: '甘肃', value: 150 },
        { name: '青海', value: 100 },
        { name: '宁夏', value: 80 },
        { name: '新疆', value: 100 }
      ],
      planePath: 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z'
    }
  },
  mounted () {
    this.generateRandomLines()
    this.initCharts()
  },
  methods: {
    generateRandomLines () {
      const points = this.points.map(point => point.value)
      const lines = []

      for (let i = 0; i < points.length; i++) {
        const from = points[i]
        let to
        do {
          to = points[Math.floor(Math.random() * points.length)]
        } while (from === to)
        lines.push({ coords: [from, to] })
      }

      this.linesData = lines
    },
    initCharts () {
      const charts = echarts.init(this.$refs['charts'])
      const option = {
        backgroundColor: '#f0f8ff', // 浅蓝色背景颜色
        tooltip: {
          trigger: 'item', // 触发类型
          formatter: function (params) {
            if (params.seriesType === 'map') {
              // 只在地图区域显示人数
              if (params.data && params.data.value) {
                return `${params.name}: ${params.data.value} 人`
              } else {
                return `${params.name}`
              }
            } else if (params.seriesType === 'effectScatter') {
              // 散点图部分的提示信息
              return `${params.name}: ${params.value[0]}, ${params.value[1]}`
            } else {
              // 线条部分的提示信息
              return `${params.name}`
            }
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
                type: 'radial', // 径向渐变
                x: 0.5, // 圆心
                y: 0.5, // 圆心
                r: 0.8, // 半径
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
          { // 散点系列数据
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
          },
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
      }

      // 地图注册，第一个参数的名字必须和option.geo.map一致
      echarts.registerMap('china', china)
      charts.setOption(option)
    }
  }
}
</script>

<style scoped>
.content {
  background-color: #f0f8ff; /* 浅蓝色背景 */
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.chart-container {
  width: 100%;
  height: 90vh; /* 高度设置为90%视口高度 */
}
</style>
