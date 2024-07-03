package com.ruoyi.employment.mapper;

import java.util.List;
import com.ruoyi.employment.domain.DepartmentsStudents;
import com.ruoyi.employment.domain.EmploymentInfo;

/**
 * 高校院系专业学生信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
public interface DepartmentsStudentsMapper 
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
     * 删除高校院系专业学生信息
     * 
     * @param id 高校院系专业学生信息主键
     * @return 结果
     */
    public int deleteDepartmentsStudentsById(Long id);

    /**
     * 批量删除高校院系专业学生信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDepartmentsStudentsByIds(Long[] ids);

    /**
     * 批量删除学生就业信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmploymentInfoByStudentIds(Long[] ids);
    
    /**
     * 批量新增学生就业信息
     * 
     * @param employmentInfoList 学生就业信息列表
     * @return 结果
     */
    public int batchEmploymentInfo(List<EmploymentInfo> employmentInfoList);
    

    /**
     * 通过高校院系专业学生信息主键删除学生就业信息信息
     * 
     * @param id 高校院系专业学生信息ID
     * @return 结果
     */
    public int deleteEmploymentInfoByStudentId(Long id);
}
