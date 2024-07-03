import request from '@/utils/request'

// 查询学生就业信息列表
export function listEmployment_info(query) {
  return request({
    url: '/employment/employment_info/list',
    method: 'get',
    params: query
  })
}

// 查询学生就业信息详细
export function getEmployment_info(employmentId) {
  return request({
    url: '/employment/employment_info/' + employmentId,
    method: 'get'
  })
}

// 新增学生就业信息
export function addEmployment_info(data) {
  return request({
    url: '/employment/employment_info',
    method: 'post',
    data: data
  })
}

// 修改学生就业信息
export function updateEmployment_info(data) {
  return request({
    url: '/employment/employment_info',
    method: 'put',
    data: data
  })
}

// 删除学生就业信息
export function delEmployment_info(employmentId) {
  return request({
    url: '/employment/employment_info/' + employmentId,
    method: 'delete'
  })
}
