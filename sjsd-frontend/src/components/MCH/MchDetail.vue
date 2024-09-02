<template>
  <div class="merchant-detail-container">
    <!-- 修改信息按钮 -->
    <div class="modify-button-container">
      <el-button type="primary" @click="openDialog('all')">修改信息</el-button>
    </div>

    <!-- 商家名称展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家名称
      </div>
      <div class="item-content">
        {{ merchant_name }}
      </div>
    </div>

    <!-- 商家类型展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家类型
      </div>
      <div class="item-content">
        {{ business_type }}
      </div>
    </div>

    <!-- 商家头像展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家头像
      </div>
      <div class="item-content">
        <el-upload
          class="avatar-uploader"
          action=""
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :disabled="!isEditing"
          :on-change="uploadAvatar"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
    </div>

    <hr>

    <!-- 是否营业展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        是否营业
      </div>
      <div class="item-content">
        {{ is_open ? '是' : '否' }}
      </div>
    </div>

    <!-- 营业时间展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        营业时间
      </div>
      <div class="item-content">
        {{ opening_hours.start }} - {{ opening_hours.end }}
      </div>
    </div>

    <!-- 商家简介展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家简介
      </div>
      <div class="item-content">
        {{ merchant_description }}
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
        {{ phone_number }}
      </div>
    </div>

    <!-- 商家地址展示 -->
    <div class="merchant-item">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        商家地址
      </div>
      <div class="item-content">
        {{ merchant_address }}
      </div>
    </div>

    <!-- 配送费、起送费、打包单价展示在同一行 -->
    <div class="merchant-item fees-display">
      <div class="item-label">
        <i class="el-icon-edit"></i>
        费用信息
      </div>
      <div class="item-content fees-content">
        <div>配送费: {{ delivery_fee }} 元</div>
        <div>起送费: {{ minimum_order_amount }} 元</div>
        <div>打包单价: {{ packaging_fee_per_item }} 元</div>
      </div>
    </div>

    <!-- 修改商家信息对话框 -->
    <el-dialog
      title="修改商家信息"
      :visible.sync="isDialogVisible"
      width="50%"
    >
      <el-form :model="form">
        <!-- 商家名称 -->
        <el-form-item label="商家名称" :label-width="formLabelWidth">
          <el-input v-model="form.merchant_name"></el-input>
        </el-form-item>

        <!-- 商家类型 -->
        <el-form-item label="商家类型" :label-width="formLabelWidth">
          <el-select v-model="form.business_type" placeholder="请选择">
            <el-option
              v-for="item in businessTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <!-- 是否营业 -->
        <el-form-item label="是否营业" :label-width="formLabelWidth">
          <el-select v-model="form.is_open" placeholder="请选择">
            <el-option label="是" :value="true"></el-option>
            <el-option label="否" :value="false"></el-option>
          </el-select>
        </el-form-item>

        <!-- 营业时间 -->
        <el-form-item label="营业时间" :label-width="formLabelWidth">
          <el-time-select
            placeholder="起始时间"
            v-model="form.opening_hours.start"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30',
            }"
            class="time-select"
          />
          <el-time-select
            placeholder="结束时间"
            v-model="form.opening_hours.end"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30',
              minTime: form.opening_hours.start,
            }"
            class="time-select"
          />
        </el-form-item>

        <!-- 商家简介 -->
        <el-form-item label="商家简介" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="form.merchant_description"></el-input>
        </el-form-item>

        <!-- 商家电话 -->
        <el-form-item label="商家电话" :label-width="formLabelWidth">
          <el-input v-model="form.phone_number"></el-input>
        </el-form-item>

        <!-- 商家地址 -->
        <el-form-item label="商家地址" :label-width="formLabelWidth">
          <el-input v-model="form.merchant_address"></el-input>
        </el-form-item>

        <!-- 配送费、起送费、打包单价 -->
        <el-form-item label="费用信息" :label-width="formLabelWidth">
          <div class="fees-container">
            <el-input-number
              v-model="form.delivery_fee"
              :min="0"
              :max="50"
              label="配送费"
              size="small"
              :controls-position="'right'"
            />
            <el-input-number
              v-model="form.minimum_order_amount"
              :min="0"
              :max="50"
              label="起送费"
              size="small"
              :controls-position="'right'"
            />
            <el-input-number
              v-model="form.packaging_fee_per_item"
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
//import api from '@/api/api'; // 请根据实际文件路径替换

export default {
data() {
  return {
    merchant_id: 1,  // 假设从其他地方传入或计算出来
    merchant_name: "XX快餐",
      phone_number: "1234567890",
      merchant_address: "XX街道XX号",
      merchant_email: "merchant@example.com",
      business_type: "", // 商家类型
      is_open: false, // 是否营业
      opening_hours: {
        start: "",
        end: ""
      }, // 营业时间
      merchant_description: "", // 商家简介
      delivery_fee: 3, // 配送费
      minimum_order_amount: 20, // 起送费
      packaging_fee_per_item: 1, // 打包单价
      isDialogVisible: false,
      isEditing: false,
    form: {
      merchant_name: "",
      phone_number: "",
      merchant_address: "",
      business_type: "",
      is_open: false,
      opening_hours: {
        start: "",
        end: ""
      },
      merchant_description: "",
      delivery_fee: 0,
      minimum_order_amount: 0,
      packaging_fee_per_item: 0,
    },
    formLabelWidth: "120px",
    businessTypeOptions: [
    { value: "快餐便当", label: "快餐便当" },
        { value: "汉堡披萨", label: "汉堡披萨" },
        { value: "奶茶咖啡", label: "奶茶咖啡" },
    ],
    imageUrl: "",
  };
},
methods: {
 /* async fetchMerchantDetails() {
    try {
      const response = await api.getMerchantDetails(this.merchant_id);
      const data = response.data;
      this.merchant_name = data.merchant_name;
      this.phone_number = data.phone_number;
      this.merchant_address = data.merchant_address;
      this.business_type = data.business_type;
      this.is_open = data.is_open;
      this.opening_hours = {
        start: data.opening_hours.split('-')[0],
        end: data.opening_hours.split('-')[1]
      };
      this.merchant_description = data.merchant_description;
      this.delivery_fee = data.delivery_fee;
      this.minimum_order_amount = data.minimum_order_amount;
      this.packaging_fee_per_item = data.packaging_fee_per_item;
      this.imageUrl = data.image_url;
    } catch (error) {
      console.error("获取商家信息失败:", error);
      this.$message.error("获取商家信息失败，请稍后重试。");
    }
  },

  async updateMerchantDetails() {
    try {
      const merchantData = {
        merchant_name: this.form.merchant_name,
        phone_number: this.form.phone_number,
        merchant_address: this.form.merchant_address,
        business_type: this.form.business_type,
        is_open: this.form.is_open,
        opening_hours: `${this.form.opening_hours.start}-${this.form.opening_hours.end}`,
        merchant_description: this.form.merchant_description,
        delivery_fee: this.form.delivery_fee,
        minimum_order_amount: this.form.minimum_order_amount,
        packaging_fee_per_item: this.form.packaging_fee_per_item,
      };
      await api.updateMerchantDetails(this.merchant_id, merchantData);
      this.$message.success("商家信息更新成功！");
      this.isDialogVisible = false;
      this.fetchMerchantDetails(); // 更新成功后重新获取商家信息
    } catch (error) {
      console.error("更新商家信息失败:", error);
      this.$message.error("更新商家信息失败，请稍后重试。");
    }
  },

  async uploadAvatar(file) {
    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await api.uploadMerchantImage(this.merchant_id, formData);
      this.imageUrl = response.data.image_url;
      this.$message.success("头像上传成功！");
    } catch (error) {
      console.error("上传头像失败:", error);
      this.$message.error("上传头像失败，请稍后重试。");
    }
  },

  async fetchBusinessTypeOptions() {
    try {
      const response = await api.getMerchantTypes();
      this.businessTypeOptions = response.data.map(item => ({
        value: item.value,
        label: item.label
      }));
    } catch (error) {
      console.error("获取商家类型选项失败:", error);
      this.$message.error("获取商家类型选项失败，请稍后重试。");
    }
  },
*/
  openDialog(type) {
    if (type === "all") {
      this.form.merchant_name = this.merchant_name;
      this.form.phone_number = this.phone_number;
      this.form.merchant_address = this.merchant_address;
      this.form.business_type = this.business_type;
      this.form.is_open = this.is_open;
      this.form.opening_hours = { ...this.opening_hours };
      this.form.merchant_description = this.merchant_description;
      this.form.delivery_fee = this.delivery_fee;
      this.form.minimum_order_amount = this.minimum_order_amount;
      this.form.packaging_fee_per_item = this.packaging_fee_per_item;
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
    const isJPGorPNG = file.type === "image/jpeg" || file.type === "image/png";
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
/*mounted() {
  this.fetchMerchantDetails(); // 在组件加载时获取商家信息
  this.fetchBusinessTypeOptions(); // 获取商家类型选项
}*/
};
</script>

<style scoped>
.merchant-detail-container {
padding: 40px;
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
