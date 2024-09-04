<template>
  <div>
    <!-- 按钮用于打开地址管理浮窗 -->
    <!-- <el-button type="primary" @click="openAddressManager">管理地址</el-button> -->

    <!-- 地址管理浮窗 -->
    <el-dialog title="管理地址" :visible.sync="addressManagerVisible" width="800px" @close="handleClose">
      <el-form>
        <el-form-item label="默认地址">
          <el-select v-model="defaultAddressId" placeholder="请选择默认地址">
            <el-option
              v-for="address in addresses"
              :key="address.id"
              :label="`${address.name} - ${address.address} - ${address.phone}`"
              :value="address.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="地址列表">
          <el-table
            :data="paginatedAddresses"
            style="width: 100%; max-height: 400px; overflow-y: auto;"
          >
            <!-- :data="paginatedAddresses" 是 Vue.js 中的单向数据绑定，而不是双向绑定。
               它将父组件中的 paginatedAddresses 数组传递给子组件（在这个例子中是 el-table 组件），
               并且子组件会根据这个数据进行渲染。 -->
            <el-table-column prop="name" label="真实姓名" />
            <el-table-column prop="address" label="地址" />
            <el-table-column prop="phone" label="电话号码" />
            <el-table-column label="操作">
              <template slot-scope="scope">  <!-- 定义作用域插槽，scope 包含当前行的数据。 -->
                <el-button size="mini" @click="editAddress(scope.row)">修改</el-button>  <!-- 修改按钮，点击时调用 editAddress 方法，传入当前行的数据。 -->
                <el-button size="mini" type="danger" @click="deleteAddress(scope.row)">删除</el-button><!-- 删除按钮，点击时调用 deleteAddress 方法，传入当前行的数据。 -->
                <!-- scope.row：当前行的数据对象。
                      scope.column：当前列的配置对象。
                      scope.$index：当前行的索引。
                      scope.store：表格的 store 对象。 -->
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pageSize"
            :total="addresses.length"
            layout="total, sizes, prev, pager, next"
            :page-sizes="[5, 10, 15]"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="openAddressForm('add')">新增地址</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 地址详细信息浮窗 -->
    <el-dialog title="地址详情" :visible.sync="addressFormVisible" width="500px">
      <el-form :model="addressForm">
        <el-form-item label="真实姓名" :label-width="formLabelWidth">
          <el-input v-model="addressForm.name" autocomplete="off"></el-input>
        </el-form-item>
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
      // addressManagerVisible: false,
      addressManagerVisible: true,
      addressFormVisible: false,
      addresses: [
        { id: 1, name: '张三', address: '北京市朝阳区', phone: '13800138000' },
        { id: 2, name: '李四', address: '上海市浦东新区', phone: '13900139000' },
        // 其他地址
      ],
      defaultAddressId: 1,  // 默认地址ID
      addressForm: {
        id: null,
        name: '',
        address: '',
        phone: '',
      },
      formLabelWidth: '120px',
      editMode: 'add', // 'add' 或 'edit'
      currentPage: 1,
      pageSize: 5,
    };
  },
  computed: {
    paginatedAddresses() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.addresses.slice(start, end);
    },
  },
  methods: {
    // openAddressManager() {
    //   this.addressManagerVisible = true;
    // },
    openAddressForm(mode) {
      this.editMode = mode;
      if (mode === 'edit' && this.addressForm.id) {
        this.addressFormVisible = true;
      } else {
        // 新增模式下清空表单
        this.addressForm = { id: null, name: '', address: '', phone: '' };
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
    handleClose() {
      this.$emit('close'); // 触发 close 事件，通知父组件
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
    handleSizeChange(size) {
      this.pageSize = size;
    },
    handleCurrentChange(page) {
      this.currentPage = page;
    },
  },
};
</script>

<style scoped>
* {
  caret-color: black;
}
.dialog-footer {
  text-align: right;
}
</style>