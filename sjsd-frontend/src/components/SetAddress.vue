<template>
    <div>
      <!-- 按钮用于打开地址管理浮窗 -->
      <el-button type="primary" @click="openAddressManager">管理地址</el-button>
  
      <!-- 地址管理浮窗 -->
      <el-dialog title="管理地址" :visible.sync="addressManagerVisible" width="500px">
        <el-form :model="selectedAddress">
          <el-form-item label="默认地址">
            <el-select v-model="defaultAddressId" placeholder="请选择默认地址">
              <el-option
                v-for="address in addresses"
                :key="address.id"
                :label="`${address.address} - ${address.phone}`"
                :value="address.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="地址列表">
            <el-table :data="addresses" style="width: 100%">
              <el-table-column prop="address" label="地址" />
              <el-table-column prop="phone" label="电话号码" />
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button size="mini" @click="editAddress(scope.row)">修改</el-button>
                  <el-button size="mini" type="danger" @click="deleteAddress(scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="openAddressForm('add')">新增地址</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
  
      <!-- 地址详细信息浮窗 -->
      <el-dialog title="地址详情" :visible.sync="addressFormVisible" width="500px">
        <el-form :model="addressForm">
          <el-form-item label="地址" :label-width="formLabelWidth">
            <el-input v-model="addressForm.address" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" :label-width="formLabelWidth">
            <el-input v-model="addressForm.phone" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addressFormVisible = false">取消</el-button>
          <el-button type="primary" @click="saveAddress">保存</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        addressManagerVisible: false,
        addressFormVisible: false,
        addresses: [
          { id: 1, address: '北京市朝阳区', phone: '13800138000' },
          { id: 2, address: '上海市浦东新区', phone: '13900139000' },
        ],
        defaultAddressId: 1,  // 默认地址ID
        addressForm: {
          id: null,
          address: '',
          phone: '',
        },
        formLabelWidth: '120px',
        editMode: 'add', // 'add' 或 'edit'
      };
    },
    methods: {
      openAddressManager() {
        this.addressManagerVisible = true;
      },
      openAddressForm(mode) {
        this.editMode = mode;
        if (mode === 'edit' && this.addressForm.id) {
          this.addressFormVisible = true;
        } else {
          // 新增模式下清空表单
          this.addressForm = { id: null, address: '', phone: '' };
          this.addressFormVisible = true;
        }
      },
      saveAddress() {
        if (this.editMode === 'add') {
          const newId = this.addresses.length ? this.addresses[this.addresses.length - 1].id + 1 : 1;
          this.addresses.push({ ...this.addressForm, id: newId });
        } else if (this.editMode === 'edit') {
          const index = this.addresses.findIndex(addr => addr.id === this.addressForm.id);
          if (index !== -1) {
            this.addresses.splice(index, 1, this.addressForm);
          }
        }
        this.addressFormVisible = false;
      },
      editAddress(address) {
        this.addressForm = { ...address };
        this.openAddressForm('edit');
      },
      deleteAddress(address) {
        this.$confirm('确定要删除这个地址吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }).then(() => {
          this.addresses = this.addresses.filter(addr => addr.id !== address.id);
          if (this.defaultAddressId === address.id && this.addresses.length) {
            this.defaultAddressId = this.addresses[0].id;
          } else if (!this.addresses.length) {
            this.defaultAddressId = null;
          }
        }).catch(() => {});
      },
    },
  };
  </script>
  
  <style scoped>
  .dialog-footer {
    text-align: right;
  }
  </style>
  