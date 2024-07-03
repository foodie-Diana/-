import request from '@/utils/request'

// 查询高校院系专业学生信息列表
export function listDepartments_students(query) {
  return request({
    url: '/employment/departments_students/list',
    method: 'get',
    params: query
  })
}

// 查询高校院系专业学生信息详细
export function getDepartments_students(id) {
  return request({
    url: '/employment/departments_students/' + id,
    method: 'get'
  })
}

// 新增高校院系专业学生信息
export function addDepartments_students(data) {
  return request({
    url: '/employment/departments_students',
    method: 'post',
    data: data
  })
}

// 修改高校院系专业学生信息
export function updateDepartments_students(data) {
  return request({
    url: '/employment/departments_students',
    method: 'put',
    data: data
  })
}

// 删除高校院系专业学生信息
export function delDepartments_students(id) {
  return request({
    url: '/employment/departments_students/' + id,
    method: 'delete'
  })
}
