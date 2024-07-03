import request from '@/utils/request'

// 查询就业分析列表
export function listEmployment_analysis(query) {
  return request({
    url: '/employment/employment_analysis/list',
    method: 'get',
    params: query
  })
}

// 查询就业分析详细
export function getEmployment_analysis(analysisId) {
  return request({
    url: '/employment/employment_analysis/' + analysisId,
    method: 'get'
  })
}

// 查询院校历年平均薪资
export function getCollegeYearlyAvgSalaries() {
  return request({
    url: '/employment/employment_analysis/collegeYearlyAvgSalaries',
    method: 'get'
  });
}
