package com.ruoyi.web.controller.employment;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.employment.domain.DepartmentsStudents;
import com.ruoyi.employment.service.IDepartmentsStudentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 高校院系专业学生信息Controller
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/employment/departments_students")
public class DepartmentsStudentsController extends BaseController
{
    @Autowired
    private IDepartmentsStudentsService departmentsStudentsService;

    /**
     * 查询高校院系专业学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('employment:departments_students:list')")
    @GetMapping("/list")
    public TableDataInfo list(DepartmentsStudents departmentsStudents)
    {
        startPage();
        List<DepartmentsStudents> list = departmentsStudentsService.selectDepartmentsStudentsList(departmentsStudents);
        return getDataTable(list);
    }

    /**
     * 导出高校院系专业学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('employment:departments_students:export')")
    @Log(title = "高校院系专业学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DepartmentsStudents departmentsStudents)
    {
        List<DepartmentsStudents> list = departmentsStudentsService.selectDepartmentsStudentsList(departmentsStudents);
        ExcelUtil<DepartmentsStudents> util = new ExcelUtil<DepartmentsStudents>(DepartmentsStudents.class);
        util.exportExcel(response, list, "高校院系专业学生信息数据");
    }

    /**
     * 获取高校院系专业学生信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('employment:departments_students:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(departmentsStudentsService.selectDepartmentsStudentsById(id));
    }

    /**
     * 新增高校院系专业学生信息
     */
    @PreAuthorize("@ss.hasPermi('employment:departments_students:add')")
    @Log(title = "高校院系专业学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DepartmentsStudents departmentsStudents)
    {
        return toAjax(departmentsStudentsService.insertDepartmentsStudents(departmentsStudents));
    }

    /**
     * 修改高校院系专业学生信息
     */
    @PreAuthorize("@ss.hasPermi('employment:departments_students:edit')")
    @Log(title = "高校院系专业学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DepartmentsStudents departmentsStudents)
    {
        return toAjax(departmentsStudentsService.updateDepartmentsStudents(departmentsStudents));
    }

    /**
     * 删除高校院系专业学生信息
     */
    @PreAuthorize("@ss.hasPermi('employment:departments_students:remove')")
    @Log(title = "高校院系专业学生信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(departmentsStudentsService.deleteDepartmentsStudentsByIds(ids));
    }
}
