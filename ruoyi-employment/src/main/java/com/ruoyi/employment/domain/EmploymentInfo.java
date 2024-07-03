package com.ruoyi.employment.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生就业信息对象 employment_info
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
public class EmploymentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 就业信息ID */
    private Long employmentId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** 薪资 */
    @Excel(name = "薪资")
    private BigDecimal salary;

    /** 就业日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "就业日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date employmentDate;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String location;

    /** 就业状态 */
    @Excel(name = "就业状态")
    private String status;

    public void setEmploymentId(Long employmentId) 
    {
        this.employmentId = employmentId;
    }

    public Long getEmploymentId() 
    {
        return employmentId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setSalary(BigDecimal salary) 
    {
        this.salary = salary;
    }

    public BigDecimal getSalary() 
    {
        return salary;
    }
    public void setEmploymentDate(Date employmentDate) 
    {
        this.employmentDate = employmentDate;
    }

    public Date getEmploymentDate() 
    {
        return employmentDate;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employmentId", getEmploymentId())
            .append("studentId", getStudentId())
            .append("companyName", getCompanyName())
            .append("position", getPosition())
            .append("salary", getSalary())
            .append("employmentDate", getEmploymentDate())
            .append("location", getLocation())
            .append("status", getStatus())
            .toString();
    }
}
