package com.ruoyi.employment.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.employment.domain.EmploymentAnalysis;
import org.apache.ibatis.annotations.MapKey;

/**
 * 就业分析Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-02
 */
public interface EmploymentAnalysisMapper
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
     * 新增就业分析
     *
     * @param employmentAnalysis 就业分析
     * @return 结果
     */
    public int insertEmploymentAnalysis(EmploymentAnalysis employmentAnalysis);

    /**
     * 修改就业分析
     *
     * @param employmentAnalysis 就业分析
     * @return 结果
     */
    public int updateEmploymentAnalysis(EmploymentAnalysis employmentAnalysis);

    /**
     * 删除就业分析
     *
     * @param analysisId 就业分析主键
     * @return 结果
     */
    public int deleteEmploymentAnalysisByAnalysisId(Long analysisId);

    /**
     * 批量删除就业分析
     *
     * @param analysisIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmploymentAnalysisByAnalysisIds(Long[] analysisIds);

    /**
     * 查询各院校历年平均薪资
     *
     * @return 各院校历年平均薪资
     */
    @MapKey("college_name")
    public List<Map<String, Object>> selectCollegeYearlyAvgSalaries();

    /**
     * 查询热门就业方向
     *
     * @return 各专业的就业人数
     */
    @MapKey("major_name")
    public List<Map<String, Object>> selectPopularMajors();


    /**
     * 查询各省份的就业人数
     *
     * @return 各省份的就业人数
     */
    @MapKey("location")
    public List<Map<String, Object>> selectPopularProvinces();

    /**
     * 查询各省份的就业人数
     *
     * @return 各省份的就业人数
     */
    @MapKey("gender")
    public List<Map<String, Object>> selectGender();

}
