package com.ruoyi.employment.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 就业分析对象 employment_analysis
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
public class EmploymentAnalysis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分析ID */
    private Long analysisId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 高校名称 */
    @Excel(name = "高校名称")
    private String collegeName;

    /** 院系名称 */
    @Excel(name = "院系名称")
    private String departmentName;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String majorName;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 就业日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "就业日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date employmentDate;

    /** 薪资 */
    @Excel(name = "薪资")
    private BigDecimal salary;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String location;

    public void setAnalysisId(Long analysisId) 
    {
        this.analysisId = analysisId;
    }

    public Long getAnalysisId() 
    {
        return analysisId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setCollegeName(String collegeName) 
    {
        this.collegeName = collegeName;
    }

    public String getCollegeName() 
    {
        return collegeName;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }
    public void setMajorName(String majorName) 
    {
        this.majorName = majorName;
    }

    public String getMajorName() 
    {
        return majorName;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setEmploymentDate(Date employmentDate) 
    {
        this.employmentDate = employmentDate;
    }

    public Date getEmploymentDate() 
    {
        return employmentDate;
    }
    public void setSalary(BigDecimal salary) 
    {
        this.salary = salary;
    }

    public BigDecimal getSalary() 
    {
        return salary;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("analysisId", getAnalysisId())
            .append("studentId", getStudentId())
            .append("collegeName", getCollegeName())
            .append("departmentName", getDepartmentName())
            .append("majorName", getMajorName())
            .append("gender", getGender())
            .append("employmentDate", getEmploymentDate())
            .append("salary", getSalary())
            .append("location", getLocation())
            .toString();
    }
}
