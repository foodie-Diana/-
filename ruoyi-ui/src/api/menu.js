import request from '@/utils/request'

// 获取路由
export const getRouters = () => {
  return request({
    url: '/getRouters',
    method: 'get'
  })
}

// 查询院校历年平均薪资
export function getCollegeYearlyAvgSalaries() {
  return request({
    url: '/employment/employment_analysis/collegeYearlyAvgSalaries',
    method: 'get'
  })
}

export function getPopularProvinces() {
  return request({
    url: '/employment/employment_analysis/popularProvinces',
    method: 'get'
  });
}

export function getGender() {
  return request({
    url: '/employment/employment_analysis/gender',
    method: 'get'
  });
}
