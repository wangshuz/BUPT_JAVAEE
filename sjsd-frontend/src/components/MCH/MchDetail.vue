<!-- 商家详情 -->
<!-- #/mch/detail -->
<template>
  <div class="merchant-detail-container">

    <!-- 商家名称展示 -->
    <div class="merchant-name-container">
        <i class="el-icon-edit"></i>
      <span class="merchant-name-label">商家名称</span>
      <span class="merchant-name-content">
        {{ merchantName }}
        <el-button type="text" @click="openDialog" class="edit-button"
          >修改</el-button
        >
      </span>
    </div>

    <!-- 修改对话框 -->
    <el-dialog title="修改商家名称" :visible.sync="isDialogVisible" width="30%">
      <el-form :model="form">
        <el-form-item label="商家名称" :label-width="formLabelWidth">
          <el-input v-model="form.merchantName"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="primary" @click="submitForm">保存</el-button>
      </div>
    </el-dialog>

    <!-- 商家品类 -->
    <i class="el-icon-edit"></i>
    <span class="merchant-name-label">商家品类</span>
    <el-select
      v-model="value1"
      placeholder="请选择"
      class="merchant-name-content"
    >
      <el-option
        v-for="item in options1"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>

    <!-- 商家头像 -->
    <i class="el-icon-edit"></i>
    <span class="merchant-name-label">商家头像</span>
    <el-upload
      class="avatar-uploader"
      action="https://jsonplaceholder.typicode.com/posts/"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
    >
      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>

    <br>

    <!-- 营业状态 -->
    <i class="el-icon-edit"></i>
    <span class="merchant-name-label">营业状态</span>
    <el-select v-model="value2" placeholder="请选择">
    <el-option
      v-for="item in options2"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <!-- 营业时间 -->
  <i class="el-icon-edit"></i>
  <span class="merchant-name-label">营业时间</span>
  <el-time-select
    placeholder="起始时间"
    v-model="startTime"
    :picker-options="{
      start: '08:30',
      step: '00:15',
      end: '18:30'
    }">
  </el-time-select>
  <el-time-select
    placeholder="结束时间"
    v-model="endTime"
    :picker-options="{
      start: '08:30',
      step: '00:15',
      end: '18:30',
      minTime: startTime
    }">
  </el-time-select>

  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      merchantName: "XX快餐", // 初始化商家名称
      isDialogVisible: false, // 控制对话框的显示与隐藏
      form: {
        merchantName: "", // 表单数据
      },
      formLabelWidth: "80px", // 表单标签宽度

      options1: [
        {
          value: "选项1",
          label: "快餐便当",
        },
        {
          value: "选项2",
          label: "包子粥店",
        },
        {
          value: "选项3",
          label: "汉堡披萨",
        },
        {
          value: "选项4",
          label: "烧烤烤肉",
        },
        {
          value: "选项5",
          label: "奶茶咖啡",
        },
      ],
      value1: "",       // 商家品类
      imageUrl: "", // 商家头像

      options2: [{
          value: '选项1',
          label: '营业中'
        }, {
          value: '选项2',
          label: '休息中'
        }],
        value2: '', // 营业状态

        startTime: '',    // 营业开始时间
        endTime: ''      // 营业结束时间
    };
  },
  methods: {
    handleSelect: function () {
      // alert("Select");
    },
    openDialog() {
      this.form.merchantName = this.merchantName; // 初始化表单数据
      this.isDialogVisible = true; // 打开对话框
    },
    closeDialog() {
      this.isDialogVisible = false; // 关闭对话框
    },
    submitForm() {
      this.merchantName = this.form.merchantName; // 更新商家名称
      this.closeDialog(); // 关闭对话框
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
  },
};
</script>
  
  <style scoped>
.merchant-detail-container {
  padding: 40px;
  padding-top: 20px;
  font-size: 17px;
}

.dialog-footer {
  text-align: right;
}

.merchant-name-container {
  padding: 10px; /* 调整为10px，使高度稍微矮一点 */
  display: flex;
  align-items: center;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.merchant-name-label {
  margin-left: 20px; /* 间距 */
  font-size: 17px;
  width: 75%;
}

.merchant-name-content {
  display: flex;
  align-items: center;

  font-weight: bold;
  justify-content: flex-end; /* 内容右对齐 */
}

.edit-button {
  margin-left: 70px;
  margin-right: 20px; /* 增加间距 */
  font-size: 17px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
  