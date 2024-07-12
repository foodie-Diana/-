<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- 其他表单项 -->
      <el-form-item label="高校ID" prop="collegeId">
        <el-input
          v-model="queryParams.collegeId"
          placeholder="请输入高校ID"
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
      <el-form-item label="院系ID" prop="departmentId">
        <el-input
          v-model="queryParams.departmentId"
          placeholder="请输入院系ID"
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
      <el-form-item label="专业ID" prop="majorId">
        <el-input
          v-model="queryParams.majorId"
          placeholder="请输入专业ID"
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
      <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
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
      <el-form-item label="出生日期" prop="dateOfBirth">
        <el-date-picker clearable
          v-model="queryParams.dateOfBirth"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="入学年份" prop="enrollmentYear">
        <el-input
          v-model="queryParams.enrollmentYear"
          placeholder="请输入入学年份"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['employment:departments_students:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['employment:departments_students:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['employment:departments_students:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employment:departments_students:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="departments_studentsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="id" />
      <el-table-column label="高校ID" align="center" prop="collegeId" />
      <el-table-column label="高校名称" align="center" prop="collegeName" />
      <el-table-column label="院系ID" align="center" prop="departmentId" />
      <el-table-column label="院系名称" align="center" prop="departmentName" />
      <el-table-column label="专业ID" align="center" prop="majorId" />
      <el-table-column label="专业名称" align="center" prop="majorName" />
      <el-table-column label="学生ID" align="center" prop="studentId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="出生日期" align="center" prop="dateOfBirth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateOfBirth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入学年份" align="center" prop="enrollmentYear" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['employment:departments_students:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employment:departments_students:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改高校院系专业学生信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="高校ID" prop="collegeId">
          <el-input v-model="form.collegeId" placeholder="请输入高校ID" />
        </el-form-item>
        <el-form-item label="高校名称" prop="collegeName">
          <el-input v-model="form.collegeName" placeholder="请输入高校名称" />
        </el-form-item>
        <el-form-item label="院系ID" prop="departmentId">
          <el-input v-model="form.departmentId" placeholder="请输入院系ID" />
        </el-form-item>
        <el-form-item label="院系名称" prop="departmentName">
          <el-input v-model="form.departmentName" placeholder="请输入院系名称" />
        </el-form-item>
        <el-form-item label="专业ID" prop="majorId">
          <el-input v-model="form.majorId" placeholder="请输入专业ID" />
        </el-form-item>
        <el-form-item label="专业名称" prop="majorName">
          <el-input v-model="form.majorName" placeholder="请输入专业名称" />
        </el-form-item>
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生ID" />
        </el-form-item>
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="出生日期" prop="dateOfBirth">
          <el-date-picker clearable
            v-model="form.dateOfBirth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="入学年份" prop="enrollmentYear">
          <el-input v-model="form.enrollmentYear" placeholder="请输入入学年份" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDepartments_students, getDepartments_students, delDepartments_students, addDepartments_students, updateDepartments_students } from "@/api/employment/departments_students";

export default {
  name: "Departments_students",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedEmploymentInfo: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 高校院系专业学生信息表格数据
      departments_studentsList: [],
      // 学生就业信息表格数据
      employmentInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        collegeId: null,
        collegeName: null,
        departmentId: null,
        departmentName: null,
        majorId: null,
        majorName: null,
        studentId: null,
        studentName: null,
        gender: null,
        dateOfBirth: null,
        enrollmentYear: null
      },
      // 表单参数
      form: {
        id: null,
        collegeId: null,
        collegeName: null,
        departmentId: null,
        departmentName: null,
        majorId: null,
        majorName: null,
        studentId: null,
        studentName: null,
        gender: null,
        dateOfBirth: null,
        enrollmentYear: null
      },
      // 表单校验
      rules: {
        collegeId: [
          { required: true, message: "高校ID不能为空", trigger: "blur" }
        ],
        collegeName: [
          { required: true, message: "高校名称不能为空", trigger: "blur" }
        ],
        departmentId: [
          { required: true, message: "院系ID不能为空", trigger: "blur" }
        ],
        departmentName: [
          { required: true, message: "院系名称不能为空", trigger: "blur" }
        ],
        majorId: [
          { required: true, message: "专业ID不能为空", trigger: "blur" }
        ],
        majorName: [
          { required: true, message: "专业名称不能为空", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" }
        ],
        studentName: [
          { required: true, message: "学生姓名不能为空", trigger: "blur" }
        ],
        enrollmentYear: [
          { required: true, message: "入学年份不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询高校院系专业学生信息列表 */
    getList() {
      this.loading = true;
      listDepartments_students(this.queryParams).then(response => {
        this.departments_studentsList = response.rows;
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
        id: null,
        collegeId: null,
        collegeName: null,
        departmentId: null,
        departmentName: null,
        majorId: null,
        majorName: null,
        studentId: null,
        studentName: null,
        gender: null,
        dateOfBirth: null,
        enrollmentYear: null
      };
      this.employmentInfoList = [];
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加高校院系专业学生信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDepartments_students(id).then(response => {
        this.form = response.data;
        this.form.enrollmentYear = response.data.enrollmentYear; // 添加这一行
        this.employmentInfoList = response.data.employmentInfoList;
        this.open = true;
        this.title = "修改高校院系专业学生信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.employmentInfoList = this.employmentInfoList;
          if (this.form.id != null) {
            updateDepartments_students(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDepartments_students(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除高校院系专业学生信息编号为"' + ids + '"的数据项？').then(function() {
        return delDepartments_students(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 学生就业信息序号 */
    rowEmploymentInfoIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 学生就业信息添加按钮操作 */
    handleAddEmploymentInfo() {
      let obj = {};
      obj.companyName = "";
      obj.position = "";
      obj.salary = "";
      obj.employmentDate = "";
      obj.location = "";
      obj.status = "";
      this.employmentInfoList.push(obj);
    },
    /** 学生就业信息删除按钮操作 */
    handleDeleteEmploymentInfo() {
      if (this.checkedEmploymentInfo.length == 0) {
        this.$modal.msgError("请先选择要删除的学生就业信息数据");
      } else {
        const employmentInfoList = this.employmentInfoList;
        const checkedEmploymentInfo = this.checkedEmploymentInfo;
        this.employmentInfoList = employmentInfoList.filter(function(item) {
          return checkedEmploymentInfo.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleEmploymentInfoSelectionChange(selection) {
      this.checkedEmploymentInfo = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employment/departments_students/export', {
        ...this.queryParams
      }, `departments_students_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
