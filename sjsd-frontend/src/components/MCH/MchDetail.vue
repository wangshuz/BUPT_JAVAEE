<template>
  <div class="merchant-detail-container">
    <!-- 商家名称展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家名称
      </div>
      <div class="item-content">
        {{ merchantName }}
        <el-button type="text" @click="openDialog" class="edit-button"
          >修改</el-button
        >
      </div>
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
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家品类
      </div>
      <div class="item-content">
        <el-select v-model="value1" placeholder="请选择" class="select-content">
          <el-option
            v-for="item in options1"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </div>
    </div>

    <!-- 商家头像 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家头像
      </div>
      <div class="item-content">
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
      </div>
    </div>

    <hr>
    <!-- 营业状态 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        营业状态
      </div>
      <div class="item-content">
        <el-select v-model="value2" placeholder="请选择" class="select-content">
          <el-option
            v-for="item in options2"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </div>
    </div>

    <!-- 营业时间 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        营业时间
      </div>
      <div class="item-content">
        <el-time-select
          placeholder="起始时间"
          v-model="startTime"
          :picker-options="{
            start: '08:30',
            step: '00:15',
            end: '18:30',
          }"
          class="time-select"
        />
        <el-time-select
          placeholder="结束时间"
          v-model="endTime"
          :picker-options="{
            start: '08:30',
            step: '00:15',
            end: '18:30',
            minTime: startTime,
          }"
          class="time-select"
        />
      </div>
    </div>

    <!-- 商家简介 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家简介
      </div>
      <div class="item-content">
        <el-input type="textarea" v-model="merchantDescription" />
      </div>
    </div>

    <hr>

    <!-- 商家电话展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家电话
      </div>
      <div class="item-content">
        {{ merchantPhone }}
        <el-button type="text" @click="openDialog('phone')" class="edit-button"
          >修改</el-button
        >
      </div>
    </div>

    <!-- 修改商家电话对话框 -->
    <el-dialog
      title="修改商家电话"
      :visible.sync="isPhoneDialogVisible"
      width="30%"
    >
      <el-form :model="form">
        <el-form-item label="商家电话" :label-width="formLabelWidth">
          <el-input v-model="form.merchantPhone"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog('phone')">取消</el-button>
        <el-button type="primary" @click="submitForm('phone')">保存</el-button>
      </div>
    </el-dialog>

    <!-- 商家地址展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家地址
      </div>
      <div class="item-content">
        {{ merchantAddress }}
        <el-button
          type="text"
          @click="openDialog('address')"
          class="edit-button"
          >修改</el-button
        >
      </div>
    </div>

    <!-- 修改商家地址对话框 -->
    <el-dialog
      title="修改商家地址"
      :visible.sync="isAddressDialogVisible"
      width="30%"
    >
      <el-form :model="form">
        <el-form-item label="商家地址" :label-width="formLabelWidth">
          <el-input v-model="form.merchantAddress"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog('address')">取消</el-button>
        <el-button type="primary" @click="submitForm('address')"
          >保存</el-button
        >
      </div>
    </el-dialog>

    <!-- 商家邮箱展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家邮箱
      </div>
      <div class="item-content">
        {{ merchantEmail }}
        <el-button type="text" @click="openDialog('email')" class="edit-button"
          >修改</el-button
        >
      </div>
    </div>

    <!-- 修改商家邮箱对话框 -->
    <el-dialog
      title="修改商家邮箱"
      :visible.sync="isEmailDialogVisible"
      width="30%"
    >
      <el-form :model="form">
        <el-form-item label="商家邮箱" :label-width="formLabelWidth">
          <el-input v-model="form.merchantEmail"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog('email')">取消</el-button>
        <el-button type="primary" @click="submitForm('email')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      merchantName: "XX快餐", // 初始化商家名称
      merchantPhone: "1234567890", // 初始化商家电话
      merchantAddress: "XX街道XX号", // 初始化商家地址
      merchantEmail: "merchant@example.com", // 初始化商家邮箱
      isNameDialogVisible: false, // 控制商家名称对话框的显示与隐藏
      isPhoneDialogVisible: false, // 控制商家电话对话框的显示与隐藏
      isAddressDialogVisible: false, // 控制商家地址对话框的显示与隐藏
      isEmailDialogVisible: false, // 控制商家邮箱对话框的显示与隐藏
      form: {
        merchantName: "", // 表单数据：商家名称
        merchantPhone: "", // 表单数据：商家电话
        merchantAddress: "", // 表单数据：商家地址
        merchantEmail: "", // 表单数据：商家邮箱
      },

      formLabelWidth: "80px", // 表单标签宽度
      options1: [
        { value: "选项1", label: "快餐便当" },
        { value: "选项2", label: "包子粥店" },
        { value: "选项3", label: "汉堡披萨" },
        { value: "选项4", label: "烧烤烤肉" },
        { value: "选项5", label: "奶茶咖啡" },
      ],
      value1: "", // 商家品类
      imageUrl: "", // 商家头像
      options2: [
        { value: "选项1", label: "营业中" },
        { value: "选项2", label: "休息中" },
      ],
      value2: "", // 营业状态
      startTime: "", // 营业开始时间
      endTime: "", // 营业结束时间
      merchantDescription: "", // 商家简介
    };
  },
  methods: {
    openDialog(type) {
      if (type === "name") {
        this.form.merchantName = this.merchantName; // 初始化表单数据
        this.isNameDialogVisible = true; // 打开商家名称对话框
      } else if (type === "phone") {
        this.form.merchantPhone = this.merchantPhone; // 初始化表单数据
        this.isPhoneDialogVisible = true; // 打开商家电话对话框
      } else if (type === "address") {
        this.form.merchantAddress = this.merchantAddress; // 初始化表单数据
        this.isAddressDialogVisible = true; // 打开商家地址对话框
      } else if (type === "email") {
        this.form.merchantEmail = this.merchantEmail; // 初始化表单数据
        this.isEmailDialogVisible = true; // 打开商家邮箱对话框
      }
    },
    closeDialog(type) {
      if (type === "name") {
        this.isNameDialogVisible = false; // 关闭商家名称对话框
      } else if (type === "phone") {
        this.isPhoneDialogVisible = false; // 关闭商家电话对话框
      } else if (type === "address") {
        this.isAddressDialogVisible = false; // 关闭商家地址对话框
      } else if (type === "email") {
        this.isEmailDialogVisible = false; // 关闭商家邮箱对话框
      }
    },
    submitForm(type) {
      if (type === "name") {
        this.merchantName = this.form.merchantName; // 更新商家名称
        this.closeDialog('name'); // 关闭商家名称对话框
      } else if (type === "phone") {
        this.merchantPhone = this.form.merchantPhone; // 更新商家电话
        this.closeDialog('phone'); // 关闭商家电话对话框
      } else if (type === "address") {
        this.merchantAddress = this.form.merchantAddress; // 更新商家地址
        this.closeDialog('address'); // 关闭商家地址对话框
      } else if (type === "email") {
        this.merchantEmail = this.form.merchantEmail; // 更新商家邮箱
        this.closeDialog('email'); // 关闭商家邮箱对话框
      }
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
  padding: 20px;
  font-size: 17px;
}

.merchant-item {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 10px;
}

.item-label {
  width: 150px;
  font-weight: bold;
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.item-content {
  flex-grow: 1;
}

.select-content,
.time-select {
  width: 50%;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 178px;
  height: 178px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.dialog-footer {
  text-align: right;
}

.edit-button {
  margin-left: 20px;
  font-size: 17px;
}
</style>
  