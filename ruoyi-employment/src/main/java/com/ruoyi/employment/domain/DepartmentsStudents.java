package com.ruoyi.employment.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 高校院系专业学生信息对象 departments_students
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
public class DepartmentsStudents extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 高校ID */
    @Excel(name = "高校ID")
    private Long collegeId;

    /** 高校名称 */
    @Excel(name = "高校名称")
    private String collegeName;

    /** 院系ID */
    @Excel(name = "院系ID")
    private Long departmentId;

    /** 院系名称 */
    @Excel(name = "院系名称")
    private String departmentName;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long majorId;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String majorName;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateOfBirth;

    /** 入学年份 */
    @Excel(name = "入学年份")
    private String enrollmentYear;

    /** 学生就业信息信息 */
    private List<EmploymentInfo> employmentInfoList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCollegeId(Long collegeId) 
    {
        this.collegeId = collegeId;
    }

    public Long getCollegeId() 
    {
        return collegeId;
    }
    public void setCollegeName(String collegeName) 
    {
        this.collegeName = collegeName;
    }

    public String getCollegeName() 
    {
        return collegeName;
    }
    public void setDepartmentId(Long departmentId) 
    {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() 
    {
        return departmentId;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }
    public void setMajorId(Long majorId) 
    {
        this.majorId = majorId;
    }

    public Long getMajorId() 
    {
        return majorId;
    }
    public void setMajorName(String majorName) 
    {
        this.majorName = majorName;
    }

    public String getMajorName() 
    {
        return majorName;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setDateOfBirth(Date dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() 
    {
        return dateOfBirth;
    }
    public void setEnrollmentYear(String enrollmentYear) 
    {
        this.enrollmentYear = enrollmentYear;
    }

    public String getEnrollmentYear() 
    {
        return enrollmentYear;
    }

    public List<EmploymentInfo> getEmploymentInfoList()
    {
        return employmentInfoList;
    }

    public void setEmploymentInfoList(List<EmploymentInfo> employmentInfoList)
    {
        this.employmentInfoList = employmentInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("collegeId", getCollegeId())
            .append("collegeName", getCollegeName())
            .append("departmentId", getDepartmentId())
            .append("departmentName", getDepartmentName())
            .append("majorId", getMajorId())
            .append("majorName", getMajorName())
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("gender", getGender())
            .append("dateOfBirth", getDateOfBirth())
            .append("enrollmentYear", getEnrollmentYear())
            .append("employmentInfoList", getEmploymentInfoList())
            .toString();
    }
}
