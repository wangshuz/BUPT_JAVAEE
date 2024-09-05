<template>
    <div>
      <el-dropdown @command="handleCommand">  <!-- 表示监听command事件 -->
        <span class="el-dropdown-link">
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
  
  export default {
    async created() {
        try {
        // 使用 await 等待异步请求完成
        const response = await api.getPresentName();
        this.personName = response; // 将返回的结果赋值给 temp
        } catch (error) {
        console.error('获取用户名失败', error);
        }
    },
    components: {
      // SetNickname,
      SetPassword,
      SetAddress,
    },
    data() {
      return {
        personName: '张三',
        // showSetNickname: false,
        showSetPassword: false,
        showSetAddress: false,
      };
    },
    methods: {
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