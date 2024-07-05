<template>
  <div class="app-container home">
    <el-row justify="center">
      <el-col :span="24">
       <hr />
        <div class="title-container">
          <h1 class="main-title">大学生就业分析平台</h1>
        </div>

      </el-col>

    </el-row>

    <el-row :gutter="20">
      <el-col :span="12">
        <!-- 热门就业方向图表 -->
        <div id="popular-majors-chart" class="chart-container">
          <PopularMajorsChart />
        </div>
      </el-col>
      <el-col :span="12">
        <!-- 就业人数男女比例图表 -->
        <div id="gg" class="chart-container">
          <EchartsGender />
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="24">
        <!-- 各省份就业人数密度图表 -->
        <div id="echarts-map" class="large-chart-container">
          <EchartsMap />
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="24">
        <!-- 各院校历年平均薪资图表 -->
        <div id="main" class="large-chart-container">
          <EChartsComponent />
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="24">
        <!-- 各省份就业人数图表 -->
        <div id="popular-provinces-chart-container" class="extra-large-chart-container">
          <PopularProvincesChart />
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <el-button
          type="primary"
          size="mini"
          icon="el-icon-cloudy"
          plain
          @click="goTarget('https://gitee.com/foodie_diana/stephen-planning-bureau')"
        >项目源码</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import EChartsComponent from './EChartsComponent.vue';
import PopularMajorsChart from './PopularMajorsChart.vue';
import EchartsMap from './EchartsMap.vue';
import PopularProvincesChart from './PopularProvincesChart.vue';
import EchartsGender from './EchartsGender.vue';

export default {
  name: "Index",
  components: {
    EChartsComponent,
    PopularMajorsChart,
    EchartsMap,
    PopularProvincesChart,
    EchartsGender
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 1vw 2vw;
    margin: 0 0 2vw;
    font-size: 1.75vw;
    border-left: 0.5vw solid #eee;
    background-color: #f9f9f9;
    border-radius: 0.5vw;
  }
  hr {
    margin-top: 2vw;
    margin-bottom: 2vw;
    border: 0;
    border-top: 0.1vw solid #eee;
  }
  .col-item {
    margin-bottom: 2vw;
  }

  ul {
    padding: 0;
    margin: 0;
    list-style-type: none;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 1.3vw;
  color: #676a6c;
  overflow-x: hidden;

  h4 {
    margin-top: 0;
  }

  h2 {
    margin-top: 1vw;
    font-size: 2.6vw;
    font-weight: 100;
  }

  p {
    margin-top: 1vw;
    line-height: 1.5;
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 4vw;
    }
  }

  .el-button {
    background-color: #42b983;
    border-color: #42b983;
    color: #fff;
    &:hover {
      background-color: #3a9c74;
      border-color: #3a9c74;
    }
  }
}

.title-container {
  text-align: center;
}

.chart-container,
.large-chart-container,
.extra-large-chart-container {
  width: 100%;
  height: 40vh;
  background-color: #fff;
  border-radius: 0.5vw;
  box-shadow: 0 0.5vw 1vw rgba(0, 0, 0, 0.1);
  padding: 1vw;
  margin-bottom: 2vw;
  transition: transform 0.3s ease, box-shadow 0.3s ease; /* 添加平滑过渡效果 */

  &:hover {
    transform: translateY(-0.5vw); /* 容器上移 */
    box-shadow: 0 1vw 2vw rgba(0, 0, 0, 0.2); /* 增加阴影 */
  }
}

.large-chart-container {
  height: 80vh; /* 调整高度 */
}

.extra-large-chart-container {
  height: 100vh; /* 更大一些 */
}

.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
