<template>
  <div class="merchant-detail-container">
    <div class="modify-button-container">
      <el-button type="primary" @click="openDialog('all')">修改信息</el-button>
    </div>

    <div class="merchant-info-container">
      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          商家名称
        </div>
        <div class="item-content">
          {{ merchantName }}
        </div>
      </div>

      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          商家类型
        </div>
        <div class="item-content">
          {{ typeName }}
        </div>
      </div>

      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          商家头像
        </div>
        <div class="item-content">
          <img v-if="avatarURL" :src="avatarURL" class="avatar" alt="商家头像" />
          <i v-else class="el-icon-user avatar-placeholder"></i>
        </div>
      </div>

      <hr />

      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          是否营业
        </div>
        <div class="item-content">
          {{ isOpen ? "是" : "否" }}
        </div>
      </div>

      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          营业时间
        </div>
        <div class="item-content">
          {{ openingHours }}
        </div>
      </div>

      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          商家简介
        </div>
        <div class="item-content">
          {{ merchantDescription }}
        </div>
      </div>

      <hr />

      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          商家电话
        </div>
        <div class="item-content">
          {{ phoneNumber }}
        </div>
      </div>

      <div class="merchant-item">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          商家地址
        </div>
        <div class="item-content">
          {{ merchantAddress }}
        </div>
      </div>

      <div class="merchant-item fees-display">
        <div class="item-label">
          <i class="el-icon-edit"></i>
          费用信息
        </div>
        <div class="item-content fees-content">
          <div>配送费: {{ deliveryFee }} 元</div>
          <div>起送费: {{ minimumOrderAmount }} 元</div>
          <div>打包单价: {{ packagingFeePerItem }} 元</div>
        </div>
      </div>
    </div>

    <!-- 修改商家信息对话框 -->
    <el-dialog
      title="修改商家信息"
      :visible.sync="isDialogVisible"
      width="50%"
      top="2vh"
      custom-class="enhanced-dialog"
    >
      <el-form :model="form">
        <el-form-item label="商家名称" :label-width="formLabelWidth">
          <el-input v-model="form.merchantName"></el-input>
        </el-form-item>

        <el-form-item label="商家类型" :label-width="formLabelWidth">
          <el-select v-model="form.typeName" placeholder="请选择">
            <el-option
              v-for="item in businessTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="商家头像" :label-width="formLabelWidth">
          <el-upload
  class="avatar-uploader"
  :action="uploadUrl"
  :show-file-list="false"
  :on-success="handleAvatarSuccess"
  :before-upload="beforeAvatarUpload"
>
            <img v-if="avatarURL" :src="avatarURL" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="是否营业" :label-width="formLabelWidth">
          <el-select v-model="form.isOpen" placeholder="请选择">
            <el-option label="是" :value="true"></el-option>
            <el-option label="否" :value="false"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="营业时间" :label-width="formLabelWidth">
          <el-time-select
            placeholder="起始时间"
            v-model="form.openingHours.start"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30',
            }"
            class="time-select"
          />
          <el-time-select
            placeholder="结束时间"
            v-model="form.openingHours.end"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30',
              minTime: form.openingHours.start,
            }"
            class="time-select"
          />
        </el-form-item>

        <el-form-item label="商家简介" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            v-model="form.merchantDescription"
          ></el-input>
        </el-form-item>

        <el-form-item label="商家电话" :label-width="formLabelWidth">
          <el-input v-model="form.phoneNumber"></el-input>
        </el-form-item>

        <el-form-item label="商家地址" :label-width="formLabelWidth">
          <el-input v-model="form.merchantAddress"></el-input>
        </el-form-item>

        <el-form-item label="费用信息" :label-width="formLabelWidth">
          <div class="fees-container">
            <el-input-number
              v-model="form.deliveryFee"
              :min="0"
              :max="50"
              label="配送费"
              size="small"
              :controls-position="'right'"
            />
            <el-input-number
              v-model="form.minimumOrderAmount"
              :min="0"
              :max="50"
              label="起送费"
              size="small"
              :controls-position="'right'"
            />
            <el-input-number
              v-model="form.packagingFeePerItem"
              :min="0"
              :max="10"
              label="打包单价"
              size="small"
              :controls-position="'right'"
            />
          </div>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog('all')">取消</el-button>
        <el-button type="primary" @click="submitForm('all')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>

import api from "@/api/api"; // 请根据实际文件路径替换

export default {
  data() {
    return {
      merchantID: 5, // 假设从其他地方传入或计算出来
      merchantName: "XX快餐",
      phoneNumber: "1234567890",
      merchantAddress: "XX街道XX号",
      merchantDescription: "",
      typeName: "健身", // 商家类型
      isOpen: false, // 是否营业
      openingHours: "8:00-22:00", // 营业时间
      deliveryFee: 3, // 配送费
      minimumOrderAmount: 20, // 起送费
      packagingFeePerItem: 1, // 打包单价
      avatarURL: "",
    
      isDialogVisible: false,
      isEditing: false,
      form: {
        merchantName: "",
        phoneNumber: "",
        merchantAddress: "",
        typeName: "",
        isOpen: false,
        openingHours: {
          start: "",
          end: "",
        },
        merchantDescription: "",
        deliveryFee: 0,
        minimumOrderAmount: 0,
        packagingFeePerItem: 0,
      },
      formLabelWidth: "120px",
      businessTypeOptions: [],
      uploadUrl: `/api/merchants/${this.merchantID}/upload-avatar`, 
      avatarURL: "",
    };
  },

 /* computed: {
    ...mapState({ merchantID: (state) => state.merchantID }), // 从 Vuex 中映射 merchantID
  },*/
  computed: {
  uploadUrl() {
    return `/api/merchants/${this.merchantID}/upload-avatar`;
  }
},

  methods: {
    handleAvatarSuccess(response) {
    if (response && response.avatarURL) {
      this.avatarURL = response.avatarURL; // 确保这里设置正确的 URL
      this.$message.success('头像上传成功');
    } else {
      this.$message.error('头像上传失败，未返回图片 URL');
    }
  },
  async fetchMerchantDetails() {
  try {
    const response = await api.getMerchantDetails(this.merchantID);
    const data = response.data;
    console.log('当前的 merchantID:', this.merchantID);
    console.log('获取的商家信息:', data);
    console.log('API 响应:', response);
    if (data) {
      // 确保 data 包含这些字段
      this.merchantName = data.merchantName || "";
      this.phoneNumber = data.phoneNumber || "";
      this.merchantAddress = data.merchantAddress || "";
      this.typeName = data.typeName || "";  // 确保 typeName 正确返回
      this.isOpen = data.isOpen || false;
      // 处理营业时间
      if (data.openingHours) {
        
        const hours = data.openingHours.split("-");
        this.openingHours = {
          start: hours[0] || "",
          end: hours[1] || "",
        };

        // 将 { start: "08:45", end: "22:00" } 转换为 "08:45-22:00"
        this.openingHours = `${hours[0]}-${hours[1]}`;

      } else {
        this.openingHours = { start: "", end: "" };
      }
      this.merchantDescription = data.merchantDescription || "";
      this.deliveryFee = data.deliveryFee || 0;
      this.minimumOrderAmount = data.minimumOrderAmount || 0;
      this.packagingFeePerItem = data.packagingFeePerItem || 0;
      this.avatarURL = data.avatarURL || "";
    } else {
      console.log("商家信息为空，可能是首次登录");
    }
  } catch (error) {
    console.error("获取商家信息失败:", error);
    this.$message.error("获取商家信息失败，请稍后重试。");
  }
},

  
  async updateMerchantDetails() {
    try {
      const merchantData = {
        merchantName: this.form.merchantName,
        phoneNumber: this.form.phoneNumber,
        merchantAddress: this.form.merchantAddress,
        typeId: this.form.typeId,
        isOpen: this.form.isOpen,
        // 格式化营业时间
        openingHours: `${this.form.openingHours.start}-${this.form.openingHours.end}`,
        merchantDescription: this.form.merchantDescription,
        deliveryFee: this.form.deliveryFee,
        minimumOrderAmount: this.form.minimumOrderAmount,
        packagingFeePerItem: this.form.packagingFeePerItem,
      };
      await api.updateMerchantDetails(this.merchantID, merchantData);
      this.$message.success("商家信息更新成功！");
      this.isDialogVisible = false;
      await this.fetchMerchantDetails(); // 更新成功后重新获取商家信息
    } catch (error) {
      console.error("更新商家信息失败:", error);
      this.$message.error("更新商家信息失败，请稍后重试。");
    }
  },


    async submitForm(type) {
      if (type === "all") {
        await this.updateMerchantDetails();
      }
    },

    async fetchBusinessTypeOptions() {
  try {
    const response = await api.getMerchantTypes();
    // 假设返回的是商家类型的数组
    this.businessTypeOptions = response.data.map((item) => ({
      value: item, // 用字符串本身作为 value
      label: item, // 用字符串本身作为 label
    }));
  } catch (error) {
    console.error("获取商家类型选项失败:", error);
    this.$message.error("获取商家类型选项失败，请稍后重试。");
  }
},
    handleAvatarSuccess(response, file) {
      this.avatarURL = response.avatarURL;  // 假设上传成功后返回图片的 URL
      this.$message.success('头像上传成功');
    },

    openDialog(type) {
    if (type === "all") {
      this.form = {
        merchantName: this.merchantName,
        phoneNumber: this.phoneNumber,
        merchantAddress: this.merchantAddress,
        typeName: this.typeName,
        isOpen: this.isOpen,
        openingHours: { ...this.openingHours }, // 确保时间格式一致
        merchantDescription: this.merchantDescription,
        deliveryFee: this.deliveryFee,
        minimumOrderAmount: this.minimumOrderAmount,
        packagingFeePerItem: this.packagingFeePerItem,
      };
      this.isEditing = true;
      this.isDialogVisible = true;
    }
  },

    closeDialog(type) {
      if (type === "all") {
        this.isDialogVisible = false;
        this.isEditing = false;
      }
    },

    beforeAvatarUpload(file) {
    const isJPGorPNG =
      file.type === "image/jpeg" || file.type === "image/png";
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isJPGorPNG) {
      this.$message.error("上传头像图片只能是 JPG 或 PNG 格式!");
    }
    if (!isLt2M) {
      this.$message.error("上传头像图片大小不能超过 2MB!");
    }
    return isJPGorPNG && isLt2M;
  },
  },

  async mounted() {
    if (this.merchantID) {
      this.fetchMerchantDetails(); // 获取商家信息
      this.fetchBusinessTypeOptions(); // 获取商家类型选项
    } else {
      console.error("merchantID 未定义，请确保已登录并获取 merchantID");
      this.$message.error("商家未登录或 merchantID 未定义");
    }
  },
};
</script>


<style scoped>
.modify-button-container {
  position: absolute; /* 绝对定位 */
  top: 20px; /* 距离顶部的距离 */
  right: 40px; /* 距离右侧的距离 */
  z-index: 10; /* 设置一个较高的层级，确保按钮在其他元素上方 */
}

.merchant-detail-container {
  position: relative; /* 相对定位 */
  padding: 40px;
  font-size: 17px;
}

.merchant-info-container {
  margin-top: 30px; /* 向下移动商家信息条目 */
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

.el-button {
  border-radius: 20px; /* 圆角按钮 */
  transition: background-color 0.3s ease; /* 背景颜色渐变 */
}

.el-button:hover {
  background-color: #409eff; /* 悬停时改变背景色 */
  box-shadow: 0 2px 12px rgba(64, 158, 255, 0.2); /* 添加阴影 */
}

.enhanced-dialog {
  border-radius: 30px;
  box-shadow: 0 4px 20px rgba(148, 129, 129, 0.1);
  transition: transform 0.3s ease;
}

.el-dialog__wrapper {
  background-color: rgba(132, 103, 103, 0.3); /* 弹窗外部背景色 */
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

.fees-display {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}

.fees-content {
  display: flex;
  gap: 30px;
  align-items: center;
}

.fees-container {
  display: flex;
  gap: 20px;
  align-items: center;
}
</style>
