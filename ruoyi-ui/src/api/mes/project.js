import request from '@/utils/request'

//查询 项目维护列表
export function getMesProjectList(){
  return request({
    methods:"get",
    url:"/mes/project"
  });
}

