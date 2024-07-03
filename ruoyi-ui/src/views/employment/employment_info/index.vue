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
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入职位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="薪资" prop="salary">
        <el-input
          v-model="queryParams.salary"
          placeholder="请输入薪资"
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
      <el-form-item label="工作地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入工作地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="就业状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择就业状态" clearable>
          <el-option
            v-for="dict in dict.type.employment_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['employment:employment_info:add']"
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
          v-hasPermi="['employment:employment_info:edit']"
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
          v-hasPermi="['employment:employment_info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employment:employment_info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employment_infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="就业信息ID" align="center" prop="employmentId" />
      <el-table-column label="学生ID" align="center" prop="studentId" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="职位" align="center" prop="position" />
      <el-table-column label="薪资" align="center" prop="salary" />
      <el-table-column label="就业日期" align="center" prop="employmentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.employmentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工作地点" align="center" prop="location" />
      <el-table-column label="就业状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.employment_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['employment:employment_info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employment:employment_info:remove']"
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

    <!-- 添加或修改学生就业信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生ID" />
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="form.position" placeholder="请输入职位" />
        </el-form-item>
        <el-form-item label="薪资" prop="salary">
          <el-input v-model="form.salary" placeholder="请输入薪资" />
        </el-form-item>
        <el-form-item label="就业日期" prop="employmentDate">
          <el-date-picker clearable
            v-model="form.employmentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择就业日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工作地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入工作地点" />
        </el-form-item>
        <el-form-item label="就业状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择就业状态">
            <el-option
              v-for="dict in dict.type.employment_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listEmployment_info, getEmployment_info, delEmployment_info, addEmployment_info, updateEmployment_info } from "@/api/employment/employment_info";

export default {
  name: "Employment_info",
  dicts: ['employment_status'],
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
      // 学生就业信息表格数据
      employment_infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        companyName: null,
        position: null,
        salary: null,
        employmentDate: null,
        location: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        employmentId: [
          { required: true, message: "就业信息ID不能为空", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" }
        ],
        companyName: [
          { required: true, message: "公司名称不能为空", trigger: "blur" }
        ],
        position: [
          { required: true, message: "职位不能为空", trigger: "blur" }
        ],
        salary: [
          { required: true, message: "薪资不能为空", trigger: "blur" }
        ],
        employmentDate: [
          { required: true, message: "就业日期不能为空", trigger: "blur" }
        ],
        location: [
          { required: true, message: "工作地点不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "就业状态不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询学生就业信息列表 */
    getList() {
      this.loading = true;
      listEmployment_info(this.queryParams).then(response => {
        this.employment_infoList = response.rows;
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
        employmentId: null,
        studentId: null,
        companyName: null,
        position: null,
        salary: null,
        employmentDate: null,
        location: null,
        status: null
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.employmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生就业信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const employmentId = row.employmentId || this.ids
      getEmployment_info(employmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生就业信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.employmentId != null) {
            updateEmployment_info(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmployment_info(this.form).then(response => {
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
      const employmentIds = row.employmentId || this.ids;
      this.$modal.confirm('是否确认删除学生就业信息编号为"' + employmentIds + '"的数据项？').then(function() {
        return delEmployment_info(employmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employment/employment_info/export', {
        ...this.queryParams
      }, `employment_info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
