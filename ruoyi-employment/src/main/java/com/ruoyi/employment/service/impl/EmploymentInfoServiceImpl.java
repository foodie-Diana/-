package com.ruoyi.employment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employment.mapper.EmploymentInfoMapper;
import com.ruoyi.employment.domain.EmploymentInfo;
import com.ruoyi.employment.service.IEmploymentInfoService;

/**
 * 学生就业信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
@Service
public class EmploymentInfoServiceImpl implements IEmploymentInfoService 
{
    @Autowired
    private EmploymentInfoMapper employmentInfoMapper;

    /**
     * 查询学生就业信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 学生就业信息
     */
    @Override
    public EmploymentInfo selectEmploymentInfoByEmploymentId(Long employmentId)
    {
        return employmentInfoMapper.selectEmploymentInfoByEmploymentId(employmentId);
    }

    /**
     * 查询学生就业信息列表
     * 
     * @param employmentInfo 学生就业信息
     * @return 学生就业信息
     */
    @Override
    public List<EmploymentInfo> selectEmploymentInfoList(EmploymentInfo employmentInfo)
    {
        return employmentInfoMapper.selectEmploymentInfoList(employmentInfo);
    }

    /**
     * 新增学生就业信息
     * 
     * @param employmentInfo 学生就业信息
     * @return 结果
     */
    @Override
    public int insertEmploymentInfo(EmploymentInfo employmentInfo)
    {
        return employmentInfoMapper.insertEmploymentInfo(employmentInfo);
    }

    /**
     * 修改学生就业信息
     * 
     * @param employmentInfo 学生就业信息
     * @return 结果
     */
    @Override
    public int updateEmploymentInfo(EmploymentInfo employmentInfo)
    {
        return employmentInfoMapper.updateEmploymentInfo(employmentInfo);
    }

    /**
     * 批量删除学生就业信息
     * 
     * @param employmentIds 需要删除的学生就业信息主键
     * @return 结果
     */
    @Override
    public int deleteEmploymentInfoByEmploymentIds(Long[] employmentIds)
    {
        return employmentInfoMapper.deleteEmploymentInfoByEmploymentIds(employmentIds);
    }

    /**
     * 删除学生就业信息信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 结果
     */
    @Override
    public int deleteEmploymentInfoByEmploymentId(Long employmentId)
    {
        return employmentInfoMapper.deleteEmploymentInfoByEmploymentId(employmentId);
    }
}
