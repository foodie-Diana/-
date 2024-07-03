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
import com.ruoyi.employment.domain.EmploymentInfo;
import com.ruoyi.employment.service.IEmploymentInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生就业信息Controller
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/employment/employment_info")
public class EmploymentInfoController extends BaseController
{
    @Autowired
    private IEmploymentInfoService employmentInfoService;

    /**
     * 查询学生就业信息列表
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_info:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmploymentInfo employmentInfo)
    {
        startPage();
        List<EmploymentInfo> list = employmentInfoService.selectEmploymentInfoList(employmentInfo);
        return getDataTable(list);
    }

    /**
     * 导出学生就业信息列表
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_info:export')")
    @Log(title = "学生就业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmploymentInfo employmentInfo)
    {
        List<EmploymentInfo> list = employmentInfoService.selectEmploymentInfoList(employmentInfo);
        ExcelUtil<EmploymentInfo> util = new ExcelUtil<EmploymentInfo>(EmploymentInfo.class);
        util.exportExcel(response, list, "学生就业信息数据");
    }

    /**
     * 获取学生就业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_info:query')")
    @GetMapping(value = "/{employmentId}")
    public AjaxResult getInfo(@PathVariable("employmentId") Long employmentId)
    {
        return success(employmentInfoService.selectEmploymentInfoByEmploymentId(employmentId));
    }

    /**
     * 新增学生就业信息
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_info:add')")
    @Log(title = "学生就业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmploymentInfo employmentInfo)
    {
        return toAjax(employmentInfoService.insertEmploymentInfo(employmentInfo));
    }

    /**
     * 修改学生就业信息
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_info:edit')")
    @Log(title = "学生就业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmploymentInfo employmentInfo)
    {
        return toAjax(employmentInfoService.updateEmploymentInfo(employmentInfo));
    }

    /**
     * 删除学生就业信息
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_info:remove')")
    @Log(title = "学生就业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employmentIds}")
    public AjaxResult remove(@PathVariable Long[] employmentIds)
    {
        return toAjax(employmentInfoService.deleteEmploymentInfoByEmploymentIds(employmentIds));
    }
}
