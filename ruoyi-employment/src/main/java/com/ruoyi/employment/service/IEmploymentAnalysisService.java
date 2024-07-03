package com.ruoyi.employment.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.employment.domain.EmploymentAnalysis;

/**
 * 就业分析Service接口
 *
 * @author ruoyi
 * @date 2024-07-02
 */
public interface IEmploymentAnalysisService
{
    /**
     * 查询就业分析
     *
     * @param analysisId 就业分析主键
     * @return 就业分析
     */
    public EmploymentAnalysis selectEmploymentAnalysisByAnalysisId(Long analysisId);

    /**
     * 查询就业分析列表
     *
     * @param employmentAnalysis 就业分析
     * @return 就业分析集合
     */
    public List<EmploymentAnalysis> selectEmploymentAnalysisList(EmploymentAnalysis employmentAnalysis);

    /**
     * 查询院校历年平均薪资
     */
    public List<Map<String, Object>> getCollegeYearlyAvgSalaries();


    public List<Map<String, Object>> getPopularMajors();



}
