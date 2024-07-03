package com.ruoyi.employment.service;

import java.util.List;
import com.ruoyi.employment.domain.DepartmentsStudents;

/**
 * 高校院系专业学生信息Service接口
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
public interface IDepartmentsStudentsService 
{
    /**
     * 查询高校院系专业学生信息
     * 
     * @param id 高校院系专业学生信息主键
     * @return 高校院系专业学生信息
     */
    public DepartmentsStudents selectDepartmentsStudentsById(Long id);

    /**
     * 查询高校院系专业学生信息列表
     * 
     * @param departmentsStudents 高校院系专业学生信息
     * @return 高校院系专业学生信息集合
     */
    public List<DepartmentsStudents> selectDepartmentsStudentsList(DepartmentsStudents departmentsStudents);

    /**
     * 新增高校院系专业学生信息
     * 
     * @param departmentsStudents 高校院系专业学生信息
     * @return 结果
     */
    public int insertDepartmentsStudents(DepartmentsStudents departmentsStudents);

    /**
     * 修改高校院系专业学生信息
     * 
     * @param departmentsStudents 高校院系专业学生信息
     * @return 结果
     */
    public int updateDepartmentsStudents(DepartmentsStudents departmentsStudents);

    /**
     * 批量删除高校院系专业学生信息
     * 
     * @param ids 需要删除的高校院系专业学生信息主键集合
     * @return 结果
     */
    public int deleteDepartmentsStudentsByIds(Long[] ids);

    /**
     * 删除高校院系专业学生信息信息
     * 
     * @param id 高校院系专业学生信息主键
     * @return 结果
     */
    public int deleteDepartmentsStudentsById(Long id);
}
