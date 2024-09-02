<template>
  <div>
    <el-avatar :size="40" :src="imageUrl || circleUrl" @click.native="showUploadDialog"></el-avatar>
    <!-- 头像上传的浮窗 -->
    <el-dialog
      title="上传头像"
      :visible.sync="dialogVisible"
                
      width="30%"
      @close="handleClose"
    ><!-- visible.sync控制模态框的显示和隐藏 和一个变量进行绑定-->   <!-- before-close在关闭模态框前执行  和一个函数进行绑定 -->
      <el-upload
        class="avatar-uploader"
        :action="uploadUrl" 
        :on-change="handleAvatarChange"
        :before-upload="beforeAvatarUpload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :file-list="fileList">
          <!-- action指定文件上传的服务器端接口 URL   on-success绑定液体个函数当文件上传成功之后调用这个函数
          before-upload指定上传前的校验用来检查文件类型和大小，只有返回值为true的时候才会上传文件    show-file-list表示不显示上传文件的列表
          on-change当文件状态发生变化时（例如文件添加、上传成功、上传失败等），会触发 handleAvatarChange 方法-->
        <img v-if="imageUrl" :src="imageUrl" class="avatar"> <!-- 如果 imageUrl 有值，显示上传的头像图片 -->
        <i v-else class="el-icon-plus avatar-uploader-icon"></i> <!-- 默认上传图标 -->
      </el-upload>
      <span slot="footer" class="dialog-footer">  <!-- slot="footer"是一个插槽，用于自定义模态框的底部 -->
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitUpload">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false, // 控制浮窗的显示和隐藏
      imageUrl: '', // 存储预览的图片URL
      fileList: [], // 存储文件列表
      uploadUrl: "https://jsonplaceholder.typicode.com/posts/", // 上传图片到Gitee仓库的URL
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
        //**res**：表示服务器返回的响应数据。上传成功后，服务器会返回一些数据，这些数据会作为 res 参数传递给 handleAvatarSuccess 函数。
        //**file**：表示上传的文件对象。file 对象包含了上传文件的详细信息。
        // 这里可以处理上传成功的逻辑，如果需要、
      this.$message({
        showClose: true,
        message: '图片已上传',
        type: 'success',
      });
      // 假设res包含了上传成功后的图片URL
      this.imageUrl = res.url;
      this.dialogVisible = false;
    },
    handleAvatarChange(file, fileList) {
      this.imageUrl = ''; // 清空之前的预览
      fileList = []; // 清空之前的文件列表
      fileList =[file];
      // if (file.raw) {
      //   this.imageUrl = URL.createObjectURL(file.raw);
      // }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return (isJPG || isPNG) && isLt2M;
    },
    submitUpload() {
      if (this.fileList.length > 0) {
        // this.$refs.upload.submit(); // 直接提交上传
        const finalFile = this.fileList[this.fileList.length - 1].raw;
        this.$emit('avatar-uploaded', finalFile); // 触发事件，通知父组件头像已上传
        this.$message({
          showClose: true,
          message: '成功更改头像',
          type: 'success'
        });
        // this.dialogVisible = false; // 关闭浮窗
      } else {
        this.$message.warning('请先上传头像');
      }
    },

    showUploadDialog() {
      this.dialogVisible = true; // 显示头像上传浮窗
    },
    handleClose() {
      this.dialogVisible = false; // 关闭浮窗
    }
  }
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
.dialog-footer {
  text-align: right;
}
</style>
