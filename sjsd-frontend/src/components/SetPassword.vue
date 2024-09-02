<template>
    <div>
      <!-- 密码设置的浮窗 -->
      <el-dialog
        title="设置密码"
        :visible.sync="dialogVisible"
        width="30%"
        @close="handleClose"
      >
        <el-form ref="passwordForm" :model="passwordForm" :rules="rules" label-width="80px">  <!--  :rules通常与el-form-item组件的prop属性配合使用，以指定哪些字段应用哪些验证规则。 -->
            <!-- 设置了 :rules 属性后，Element UI 会自动在适当的时机（例如，当用户完成输入或光标离开输入框时，依据 trigger 属性的设置）对表单项进行验证      即使没有写验证回调函数 -->
          <el-form-item label="新密码" prop="password">
            <el-input
              type="password"
              v-model="passwordForm.password"
              autocomplete="new-password"   
              show-password
            ></el-input><!-- autocomplete="new-password"设置自动完成属性，提示浏览器这是一个新密码输入框。   show-password：允许用户切换密码的可见性。-->
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input
              type="password"
              v-model="passwordForm.confirmPassword"
              autocomplete="new-password"
              show-password
            ></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="savePassword">保存</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        dialogVisible: true, // 控制浮窗显示
        passwordForm: {
          password: '', // 存储用户输入的新密码
          confirmPassword: '' // 存储用户输入的确认密码
        },
        rules: {
          password: [
            { required: true, message: '请输入新密码', trigger: 'blur' },   /* trigger: 'blur' 通常用于表单验证规则，表示当输入框失去焦点（blur 事件）时触发验证 */
            { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
          ],
          confirmPassword: [
            { required: true, message: '请再次输入新密码', trigger: 'blur' },
            { validator: this.confirmPasswordValidator, trigger: 'blur' }  /* 它指定了一个自定义验证函数 confirmPasswordValidator，用于验证输入的值是否符合特定条件 */
          ]
        }
      };
    },
    methods: {
      savePassword() {
        this.$refs.passwordForm.validate((valid) => {   /* $refs是一个对象，包含了模板中所有带有ref属性的DOM元素的引用。     */
            /* validate()：这是Element UI表单组件提供的一个方法，用于触发表单验证。它接受一个回调函数，
            该回调函数带有一个参数valid，表示验证是否通过（true表示通过，false表示失败）。 */
            /* valid 通常用于表单验证规则，表示当输入框失去焦点（blur 事件）时触发验证 */
            /*   =>里面的就是验证回调函数 */
          if (valid) {
            // 这里可以添加发送新密码到服务器的代码
            this.$message({
              message: '密码设置成功',
              type: 'success'
            });
            this.dialogVisible = false; // 关闭浮窗
          } else {
            console.log('表单验证失败');
            return false;
          }
        });
      },
      confirmPasswordValidator(rule, value, callback) {  //value是用户现在在表单中这个位置输入的值，也就是确认密码中输入的值
        
        /* callback用于在验证过程结束时调用，以通知验证系统验证是否通过。它是一个必须被调用的回调函数，你可以传递一个Error对象来表示验证失败，或者不传递任何参数（或传递null）来表示验证通过。 */
        if (value !== this.passwordForm.password) {
          callback(new Error('两次输入的密码不一致'));
        } else {
          callback();
        }
      },
      handleClose() {
        this.dialogVisible = false; // 关闭浮窗
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