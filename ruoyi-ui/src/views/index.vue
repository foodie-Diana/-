<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">

        <h2>高校就业分析平台</h2>
        <!-- ECharts 图表 -->
        <div id="main" style="width: 100%; height: 400px;">
          <EChartsComponent />
        </div>

        <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p>
        <p>
          <!-- ECharts 图表 -->
          <h2>热门就业方向</h2>
          <div id="popular-majors-chart" style="width: 100%; height: 400px;">
            <PopularMajorsChart />
          </div>
        </p>

        <p>
          <!-- ECharts 图表 -->
          <h2>热门就业方向</h2>
          <div id="echarts-map">
            <EchartsMap />
          </div>
        </p>

        <p>
          <!-- ECharts 图表 -->
          <h2>各省份就业人数</h2>
          <div id="popular-provinces-chart-container" style="width: 100%; height: 400px;">
            <PopularProvincesChart />
          </div>
        </p>

        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('https://gitee.com/foodie_diana/stephen-planning-bureau')"
            >项目源码</el-button
          >
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('http://ruoyi.vip')"
            >访问主页</el-button
          >
        </p>
      </el-col>
      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import EChartsComponent from './EChartsComponent.vue';
import PopularMajorsChart from './PopularMajorsChart.vue';
import EchartsMap from './EchartsMap.vue';
import PopularProvincesChart from './PopularProvincesChart.vue';

export default {
  name: "Index",
  components: {
    EChartsComponent,
    PopularMajorsChart,
    EchartsMap,
    PopularProvincesChart
  },
  data() {
    return {
      version: "3.8.7"
    };
  },
  created() {
    // Initialize charts if needed here
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
