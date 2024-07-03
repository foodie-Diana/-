<template>
  <div>
    <h2>项目维护列表</h2>
  </div>
</template>

<script>
  import { getMesProjectList } from '@/api/mes/project.js';
  export default{
    name: "mes",
    created(){

      this.getList();

    },
    methods:{
      getList(){
        //promise编程
        getMesProjectList().then(body =>{
          console.info(body);
        }).catch(e => {});
      }
    }
  }
</script>

<style>
</style>
