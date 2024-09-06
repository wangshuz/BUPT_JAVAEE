<template>
    <div>
      <el-dropdown @command="handleCommand">  <!-- 表示监听command事件 -->
        <span class="el-dropdown-link" @click="changeName" v-bind:title="personName">
          {{personName}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <!-- <el-dropdown-item command="setNickname">设置昵称</el-dropdown-item> -->
          <el-dropdown-item command="setPassword">设置密码</el-dropdown-item>
          <el-dropdown-item command="setAddress">设置地址</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
  
      <!-- 引入三个浮窗组件 -->
      <!-- <SetNickname v-if="showSetNickname" @close="showSetNickname = false" /> -->
      <SetPassword v-if="showSetPassword" @close="showSetPassword = false" />
      <SetAddress v-if="showSetAddress" @close="showSetAddress = false" />
    </div>
  </template>
  
  <script>
  // import SetNickname from './SetNickname.vue';
  import SetPassword from './SetPassword.vue';
  import SetAddress from './SetAddress.vue';
  import api from '../api/api.js';
  export default {
    async mounted() {
      try {
        const id = 1; // 假设 ID 是 1，你可以根据需要获取实际的 ID
        const flag = 0; // 假设是商家，传递 0，客户为 1

        // 调用后端 API 获取用户名
        const response = await api.getPresentName(id, flag);
          // this.personName = response.data.data.personName;// 赋值用户名
          console.log('获取用户名成功', this.personName);
      } catch (error) {
        console.error('获取用户名时发生错误', error);
      }
    },
    async created() {
      
    },
    components: {
      // SetNickname,
      SetPassword,
      SetAddress,
    },
    data() {
      return {
        personName:"张三",
        // showSetNickname: false,
        showSetPassword: false,
        showSetAddress: false,
      };
    },
    methods: {
      // changeName() {
      //   // 模拟名称变化
      //   this.personName=this.personName;
      // },
      handleCommand(command) {
        // this.showSetNickname = false;
        this.showSetPassword = false;
        this.showSetAddress = false;
  
        // if (command === 'setNickname') {
        //   this.showSetNickname = true;
        // } else if (command === 'setPassword') {
        //   this.showSetPassword = true;
        // } else if (command === 'setAddress') {
        //   this.showSetAddress = true;
        // }
        if (command === 'setPassword') {
          this.showSetPassword = true;
        } else if (command === 'setAddress') {
          this.showSetAddress = true;
        }
      },
    },
  };
  </script>
  
  <style>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
  </style>