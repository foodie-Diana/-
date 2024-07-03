package com.ruoyi.employment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employment.mapper.EmploymentAnalysisMapper;
import com.ruoyi.employment.domain.EmploymentAnalysis;
import com.ruoyi.employment.service.IEmploymentAnalysisService;

/**
 * 就业分析Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-02
 */
@Service
public class EmploymentAnalysisServiceImpl implements IEmploymentAnalysisService
{
    @Autowired
    private EmploymentAnalysisMapper employmentAnalysisMapper;

    /**
     * 查询就业分析
     *
     * @param analysisId 就业分析主键
     * @return 就业分析
     */
    @Override
    public EmploymentAnalysis selectEmploymentAnalysisByAnalysisId(Long analysisId)
    {
        return employmentAnalysisMapper.selectEmploymentAnalysisByAnalysisId(analysisId);
    }

    /**
     * 查询就业分析列表
     *
     * @param employmentAnalysis 就业分析
     * @return 就业分析
     */
    @Override
    public List<EmploymentAnalysis> selectEmploymentAnalysisList(EmploymentAnalysis employmentAnalysis)
    {
        return employmentAnalysisMapper.selectEmploymentAnalysisList(employmentAnalysis);
    }

    // 其他方法省略

    @Override
    public List<Map<String, Object>> getCollegeYearlyAvgSalaries() {
        return employmentAnalysisMapper.selectCollegeYearlyAvgSalaries();
    }

}
