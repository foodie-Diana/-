<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="高校名称" prop="collegeName">
        <el-input
          v-model="queryParams.collegeName"
          placeholder="请输入高校名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="院系名称" prop="departmentName">
        <el-input
          v-model="queryParams.departmentName"
          placeholder="请输入院系名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业名称" prop="majorName">
        <el-input
          v-model="queryParams.majorName"
          placeholder="请输入专业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="就业日期" prop="employmentDate">
        <el-date-picker clearable
          v-model="queryParams.employmentDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择就业日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="薪资" prop="salary">
        <el-input
          v-model="queryParams.salary"
          placeholder="请输入薪资"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入工作地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employment:employment_analysis:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employment_analysisList" @selection-change="handleSelectionChange">
      <el-table-column label="分析ID" align="center" prop="analysisId" />
      <el-table-column label="学生ID" align="center" prop="studentId" />
      <el-table-column label="高校名称" align="center" prop="collegeName" />
      <el-table-column label="院系名称" align="center" prop="departmentName" />
      <el-table-column label="专业名称" align="center" prop="majorName" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="就业日期" align="center" prop="employmentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.employmentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="薪资" align="center" prop="salary" />
      <el-table-column label="工作地点" align="center" prop="location" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改就业分析对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- ECharts 图表 -->
    <!-- ECharts 图表 -->
    <div id="main1" style="width: 100%; height: 400px;">
      <!-- ECharts 组件 -->
          <EChartsComponent />
    </div>

  </div>
</template>

<script>
import { listEmployment_analysis, getEmployment_analysis, delEmployment_analysis, addEmployment_analysis, updateEmployment_analysis, getCollegeYearlyAvgSalaries } from "@/api/employment/employment_analysis";
import EChartsComponent from './EChartsComponent.vue';


export default {
  name: "Employment_analysis",
    components: {
      EChartsComponent
    },
  name: "Employment_analysis",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 就业分析表格数据
      employment_analysisList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        analysisId: null,
        studentId: null,
        collegeName: null,
        departmentName: null,
        majorName: null,
        gender: null,
        employmentDate: null,
        salary: null,
        location: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" }
        ],
        collegeName: [
          { required: true, message: "高校名称不能为空", trigger: "blur" }
        ],
        departmentName: [
          { required: true, message: "院系名称不能为空", trigger: "blur" }
        ],
        majorName: [
          { required: true, message: "专业名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();

  },
  methods: {
    /** 查询就业分析列表 */
    getList() {
      this.loading = true;
      listEmployment_analysis(this.queryParams).then(response => {
        this.employment_analysisList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        analysisId: null,
        studentId: null,
        collegeName: null,
        departmentName: null,
        majorName: null,
        gender: null,
        employmentDate: null,
        salary: null,
        location: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employment/employment_analysis/export', {
        ...this.queryParams
      }, `employment_analysis_${new Date().getTime()}.xlsx`)
    },


  }
};
</script>
