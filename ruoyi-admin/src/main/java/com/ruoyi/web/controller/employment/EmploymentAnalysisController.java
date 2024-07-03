package com.ruoyi.web.controller.employment;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.employment.domain.EmploymentAnalysis;
import com.ruoyi.employment.service.IEmploymentAnalysisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 就业分析Controller
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/employment/employment_analysis")
public class EmploymentAnalysisController extends BaseController
{
    @Autowired
    private IEmploymentAnalysisService employmentAnalysisService;

    /**
     * 查询就业分析列表
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_analysis:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmploymentAnalysis employmentAnalysis)
    {
        startPage();
        List<EmploymentAnalysis> list = employmentAnalysisService.selectEmploymentAnalysisList(employmentAnalysis);
        return getDataTable(list);
    }

    /**
     * 导出就业分析列表
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_analysis:export')")
    @Log(title = "就业分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmploymentAnalysis employmentAnalysis)
    {
        List<EmploymentAnalysis> list = employmentAnalysisService.selectEmploymentAnalysisList(employmentAnalysis);
        ExcelUtil<EmploymentAnalysis> util = new ExcelUtil<EmploymentAnalysis>(EmploymentAnalysis.class);
        util.exportExcel(response, list, "就业分析数据");
    }

    /**
     * 获取就业分析详细信息
     */
    @PreAuthorize("@ss.hasPermi('employment:employment_analysis:query')")
    @GetMapping(value = "/{analysisId}")
    public AjaxResult getInfo(@PathVariable("analysisId") Long analysisId)
    {
        return success(employmentAnalysisService.selectEmploymentAnalysisByAnalysisId(analysisId));
    }

    /**
     * 获取院校历年平均薪资
     */
    @GetMapping("/collegeYearlyAvgSalaries")
    public AjaxResult getCollegeYearlyAvgSalaries() {
        List<Map<String, Object>> data = employmentAnalysisService.getCollegeYearlyAvgSalaries();
        return AjaxResult.success(data);
    }

}
