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
        <!-- 热门就业方向图表 -->
        <div id="popular-majors-chart" style="width: 100%; height: 450px;">
          <PopularMajorsChart />
        </div>
        <!-- 各省份就业人数图表 -->
        <div id="popular-provinces-chart-container" style="width: 100%; height: 400px;">
          <PopularProvincesChart />
        </div>
      </el-col>
      <el-col :span="12">
        <!-- 各省份就业人数密度图表 -->
        <div id="echarts-map" style="width: 100%; height: 800px;">
          <EchartsMap />
        </div>
      </el-col>
      <el-col :span="6">
        <!-- 就业人数男女比例图表 -->
        <div id="gg" style="width: 100%; height: 470px;">
          <EchartsGender />
        </div>
        <!-- 各院校历年平均薪资图表 -->
        <div id="main" style="width: 100%; height: 400px;">
          <EChartsComponent />
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
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

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
      padding-inline-start: 40px;
    }
  }
}
</style>
