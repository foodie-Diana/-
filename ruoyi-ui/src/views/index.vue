<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <hr />
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="12">
        <h2>高校就业分析平台</h2>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="6">
        <!-- 各院校历年平均薪资图表 -->
        <div id="main" class="chart-container">
          <EChartsComponent />
        </div>
        <!-- 各省份就业人数图表 -->
        <div id="popular-provinces-chart-container" class="chart-container">
          <PopularProvincesChart />
        </div>
      </el-col>
      <el-col :span="12">
        <!-- 各省份就业人数密度图表 -->
        <div id="echarts-map" class="large-chart-container">
          <EchartsMap />
        </div>
      </el-col>
      <el-col :span="6">
        <!-- 就业人数男女比例图表 -->
        <div id="gg" class="chart-container">
          <EchartsGender />
        </div>
        <!-- 热门就业方向图表 -->
        <div id="popular-majors-chart" class="chart-container">
          <PopularMajorsChart />
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
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 1.3vw;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

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

    b {
      font-weight: 700;
    }
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
}

.chart-container {
  width: 100%;
  height: 40vh; /* 使用视口高度单位 */
}

.large-chart-container {
  width: 100%;
  height: 80vh; /* 使用视口高度单位 */
}
</style>
