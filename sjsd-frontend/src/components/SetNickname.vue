<template>
    <div>
      <!-- 昵称设置的浮窗 -->
      <el-dialog
        title="设置昵称"
        :visible.sync="dialogVisible"
        width="30%"
        @close="handleClose"
      >
        <el-form ref="nicknameForm" :model="nicknameForm" label-width="80px">  <!-- :model  等价于 v-model进行双向绑定 -->
          <el-form-item label="昵称" :prop="'nickname'">    <!-- :prop属性用于指定表单项应该绑定和验证的模型属性的名称 -->
            <el-input v-model="nicknameForm.nickname" autocomplete="off"></el-input>    <!-- 设置自动完成功能为关闭，防止浏览器自动填充输入内容 -->
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveNickname">保存</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        dialogVisible: true, // 控制浮窗显示
        nicknameForm: {
          nickname: '' // 存储用户输入的昵称
        }
      };
    },
    methods: {
      saveNickname() {
        // 检查昵称是否重复
        if (this.isNicknameDuplicate(this.nicknameForm.nickname)) {
          this.$message({
            message: '昵称已存在，请选择其他昵称',
            type: 'warning'
          });
        } else {
          this.$message({
            message: '昵称设置成功',
            type: 'success'
          });
          this.dialogVisible = false; // 关闭浮窗
        }
      },
      isNicknameDuplicate(nickname) {
        // 模拟检查昵称是否重复的逻辑
        // 这里返回true表示昵称重复，实际应用中需要调用后端API进行验证
        return ['zhangsan', 'lisi', 'wangwu'].includes(nickname);
      },
      handleClose() {
        this.$emit('close'); // 触发 close 事件，通知父组件
      }
    }
  };
  </script>
  
  <style scoped>
  *{
     caret-color: black;
  }
  .dialog-footer {
    text-align: right;
  }
  </style>