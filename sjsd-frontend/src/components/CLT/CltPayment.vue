<template>
  <el-dialog :visible.sync="visible" width="600px">  <!-- 这是一个整个的大对话框 -->
    <div class="payment-popup">  <!-- 这是一个对话框的内容区域  对话框内只有这一个大的div -->
      <!-- 倒计时 -->
      <div class="countdown"> 
        支付剩余时间还有 {{ countdown }}  秒
      </div>
      
      <!-- 步骤条 -->
      <el-steps :active="activeStep" finish-status="success">
        <!-- activeStep 是一个 Vue.js 数据属性，表示当前步骤的索引（从 0 开始）。
              当 activeStep 的值变化时，步骤条会自动更新以反映当前的步骤。 -->
        <!-- success 是一个预定义的状态，表示步骤完成时显示成功的样式（通常是绿色的对勾）。 -->
        <el-step title="选择支付方式"></el-step>
        <el-step title="扫码支付"></el-step>
        <el-step title="支付完成"></el-step>
      </el-steps>
      
      <!-- 步骤一：选择支付方式 -->
      <div v-if="activeStep === 0">
        <el-select v-model="selectedPayment" placeholder="请选择支付方式">
          <!-- 把用户选择的支付方式和selectedPayment绑定在一起 -->
          <el-option label="支付宝" value="alipay"></el-option>
          <el-option label="微信" value="wechat"></el-option>
        </el-select>
        <el-button type="primary" @click="nextStep">下一步</el-button>
        <!-- 设置按钮为“主要按钮”的样式，用一个强调色表示（比如蓝色） -->
      </div>
      
      <!-- 步骤二：展示二维码 -->
      <div v-if="activeStep === 1">
        <div v-if="selectedPayment === 'wechat'">
          <img src="path_to_wechat_qr_code" alt="微信支付二维码"/>
        </div>
        <div v-else-if="selectedPayment === 'alipay'">
          <img src="path_to_alipay_qr_code" alt="支付宝支付二维码"/>
        </div>
        <el-button type="primary" @click="simulatePaymentSuccess">完成支付</el-button>
      </div>
      
      <!-- 步骤三：支付成功 -->
      <div v-if="activeStep === 2">
        <el-result
          icon="success"
          title="支付成功"
          sub-title="商家开始备餐。。。">
        </el-result>
        <!--  el-result 是一个组件，用于展示简单的成功或失败的提示信息。
         这里使用了 icon 属性来设置图标，title 属性设置标题，sub-title 属性设置副标题。 -->
      </div>
    </div>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      visible: true,
      countdown: 480, // 倒计时，秒
      activeStep: 0,
      selectedPayment: ''
    };
  },
  methods: {
    nextStep() {
      if (this.selectedPayment) {  //表面用户已经选择了用什么支付，再点击下一步，就进到activeStep = 1
        this.activeStep = 1;
      } else {
        this.$message.error('请选择支付方式');  
        // 表面用户还没有选择支付方式，$message 方法显示一个错误消息，提示用户“请选择支付方式”。
      }
    },
    simulatePaymentSuccess() {
      this.activeStep = 2;
      setTimeout(() => {
        this.visible = false;
      }, 3000); // 支付成功后3秒自动关闭弹窗
      //settimeout用于在指定的时间（以毫秒为单位）后执行一个回调函数。
      // 回调的是一个匿名函数，函数体是this.visible = false;
    },
    startCountdown() {
      const timer = setInterval(() => {
        if (this.countdown > 0) {
          this.countdown--;
        } else {
          clearInterval(timer);
          this.visible = false;
        }
      }, 1000);
    }
  },
  mounted() {
    this.startCountdown();
  }
};
</script>

<style scoped>
.payment-popup {
  text-align: center;
}

.countdown {
  font-size: 20px;
  margin-bottom: 20px;
}
</style>