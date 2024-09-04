<template>
  <div class="side-bar">
    <el-col :span="24">
      <el-menu
          default-active="0"
          class="menu-v"
          text-color="#000"
          active-text-color="#ffd04b" 
          >
          <span v-for="(item, idx) in listData" :key="idx">
          <el-menu-item :index="idx" @click="clickHandler(item.cb)" >
              <i class="el-icon-dish"></i>
              <span slot="title">{{item.typeName}}</span>
          </el-menu-item>
          </span>
      </el-menu>
    </el-col>
  </div>
</template>

<script>

export default{
  // 接收参数格式
  // [
  //   {
  //     "id":Number,
  //     "typeName":"String",
  //     "cb":Function(){},         //回调函数，点击触发
  //   },
  // ]

  props: {
    listData:{
      type:Array,
      Required:true,
      default: () => [],
      validator(value) {
        if (!Array.isArray(value)) return false;
        return value.every(item => {
          return typeof item === 'object' && item !== null &&
            typeof item.id === 'number' &&
            typeof item.typeName === 'string' &&
            ('cb' in item ? typeof item.onClick === 'function' : false);
        });
      }
    }
  },
  data(){
    return{

    }
  },
  methods:{
    clickHandler(cb) {
      if(typeof cb === 'function'){
        cb.call(this);
      }
    }
  }

}

</script>

<style>
.side-bar{
  background-color:#CECECE ;
  width:160px;
  height: 75vh;
  position: absolute;
  overflow-x:hidden;
  overflow-y:auto;
  border-radius: 15px;
  left: 5%;
  top: 15%;
  opacity: 0.7;
  z-index: 100;
}

.side-bar::-webkit-scrollbar {
  display: none;
}

.menu-v .el-menu-item{
  opacity: 1;
  font-size: 20px;
  background-color:#CECECE ;
}

.menu-v .el-menu-item.is-active{
  opacity: 1;
  font-size: 20px;
  background-color: #333;
}




</style>