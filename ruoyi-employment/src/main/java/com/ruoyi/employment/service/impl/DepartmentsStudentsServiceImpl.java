package com.ruoyi.employment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.employment.domain.EmploymentInfo;
import com.ruoyi.employment.mapper.DepartmentsStudentsMapper;
import com.ruoyi.employment.domain.DepartmentsStudents;
import com.ruoyi.employment.service.IDepartmentsStudentsService;

/**
 * 高校院系专业学生信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
@Service
public class DepartmentsStudentsServiceImpl implements IDepartmentsStudentsService 
{
    @Autowired
    private DepartmentsStudentsMapper departmentsStudentsMapper;

    /**
     * 查询高校院系专业学生信息
     * 
     * @param id 高校院系专业学生信息主键
     * @return 高校院系专业学生信息
     */
    @Override
    public DepartmentsStudents selectDepartmentsStudentsById(Long id)
    {
        return departmentsStudentsMapper.selectDepartmentsStudentsById(id);
    }

    /**
     * 查询高校院系专业学生信息列表
     * 
     * @param departmentsStudents 高校院系专业学生信息
     * @return 高校院系专业学生信息
     */
    @Override
    public List<DepartmentsStudents> selectDepartmentsStudentsList(DepartmentsStudents departmentsStudents)
    {
        return departmentsStudentsMapper.selectDepartmentsStudentsList(departmentsStudents);
    }

    /**
     * 新增高校院系专业学生信息
     * 
     * @param departmentsStudents 高校院系专业学生信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDepartmentsStudents(DepartmentsStudents departmentsStudents)
    {
        int rows = departmentsStudentsMapper.insertDepartmentsStudents(departmentsStudents);
        insertEmploymentInfo(departmentsStudents);
        return rows;
    }

    /**
     * 修改高校院系专业学生信息
     * 
     * @param departmentsStudents 高校院系专业学生信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDepartmentsStudents(DepartmentsStudents departmentsStudents)
    {
        departmentsStudentsMapper.deleteEmploymentInfoByStudentId(departmentsStudents.getId());
        insertEmploymentInfo(departmentsStudents);
        return departmentsStudentsMapper.updateDepartmentsStudents(departmentsStudents);
    }

    /**
     * 批量删除高校院系专业学生信息
     * 
     * @param ids 需要删除的高校院系专业学生信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDepartmentsStudentsByIds(Long[] ids)
    {
        departmentsStudentsMapper.deleteEmploymentInfoByStudentIds(ids);
        return departmentsStudentsMapper.deleteDepartmentsStudentsByIds(ids);
    }

    /**
     * 删除高校院系专业学生信息信息
     * 
     * @param id 高校院系专业学生信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDepartmentsStudentsById(Long id)
    {
        departmentsStudentsMapper.deleteEmploymentInfoByStudentId(id);
        return departmentsStudentsMapper.deleteDepartmentsStudentsById(id);
    }

    /**
     * 新增学生就业信息信息
     * 
     * @param departmentsStudents 高校院系专业学生信息对象
     */
    public void insertEmploymentInfo(DepartmentsStudents departmentsStudents)
    {
        List<EmploymentInfo> employmentInfoList = departmentsStudents.getEmploymentInfoList();
        Long id = departmentsStudents.getId();
        if (StringUtils.isNotNull(employmentInfoList))
        {
            List<EmploymentInfo> list = new ArrayList<EmploymentInfo>();
            for (EmploymentInfo employmentInfo : employmentInfoList)
            {
                employmentInfo.setStudentId(id);
                list.add(employmentInfo);
            }
            if (list.size() > 0)
            {
                departmentsStudentsMapper.batchEmploymentInfo(list);
            }
        }
    }
}
