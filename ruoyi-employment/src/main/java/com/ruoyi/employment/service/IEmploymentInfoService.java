package com.ruoyi.employment.service;

import java.util.List;
import com.ruoyi.employment.domain.EmploymentInfo;

/**
 * 学生就业信息Service接口
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
public interface IEmploymentInfoService 
{
    /**
     * 查询学生就业信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 学生就业信息
     */
    public EmploymentInfo selectEmploymentInfoByEmploymentId(Long employmentId);

    /**
     * 查询学生就业信息列表
     * 
     * @param employmentInfo 学生就业信息
     * @return 学生就业信息集合
     */
    public List<EmploymentInfo> selectEmploymentInfoList(EmploymentInfo employmentInfo);

    /**
     * 新增学生就业信息
     * 
     * @param employmentInfo 学生就业信息
     * @return 结果
     */
    public int insertEmploymentInfo(EmploymentInfo employmentInfo);

    /**
     * 修改学生就业信息
     * 
     * @param employmentInfo 学生就业信息
     * @return 结果
     */
    public int updateEmploymentInfo(EmploymentInfo employmentInfo);

    /**
     * 批量删除学生就业信息
     * 
     * @param employmentIds 需要删除的学生就业信息主键集合
     * @return 结果
     */
    public int deleteEmploymentInfoByEmploymentIds(Long[] employmentIds);

    /**
     * 删除学生就业信息信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 结果
     */
    public int deleteEmploymentInfoByEmploymentId(Long employmentId);
}
