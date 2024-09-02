<template>
    <div>
      <!-- 按钮用于打开地址管理浮窗 -->
      <!-- <el-button type="primary" @click="openAddressManager">管理地址</el-button> -->
  
      <!-- 地址管理浮窗 -->
      <el-dialog title="管理地址" :visible.sync="addressManagerVisible" width="500px">
        <el-form>  <!-- 包裹了整个表单 -->
          <el-form-item label="默认地址">  <!-- 包裹了每个表单控件 -->
            <el-select v-model="defaultAddressId" placeholder="请选择默认地址">  <!-- 下拉选择框的选项 -->
              <el-option
                v-for="address in addresses"
                :key="address.id"
                :label="`${address.address} - ${address.phone}`"
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
            <!-- @size-change="handleSizeChange":
                监听分页组件的 size-change 事件，当每页显示的条目数量改变时触发 handleSizeChange 方法。
                @current-change="handleCurrentChange":

                监听分页组件的 current-change 事件，当当前页码改变时触发 handleCurrentChange 方法。
                :current-page="currentPage":

                绑定当前页码，currentPage 是一个 Vue 实例中的数据属性，表示当前显示的页码。
                :page-size="pageSize":

                绑定每页显示的条目数量，pageSize 是一个 Vue 实例中的数据属性，表示每页显示的条目数量。
                :total="addresses.length":

                绑定总条目数，addresses.length 表示 addresses 数组的长度，即总条目数。
                layout="total, sizes, prev, pager, next":

                配置分页组件的布局，包含总条目数显示、每页条目数选择器、上一页按钮、页码按钮和下一页按钮。
                :page-sizes="[5, 10, 15]":

                配置每页显示条目数量的选项，用户可以选择每页显示 5、10 或 15 条数据。 -->
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
        // addressManagerVisible: false,
        addressManagerVisible: true,
        addressFormVisible: false,
        addresses: [
          { id: 1, address: '北京市朝阳区', phone: '13800138000' },
          { id: 2, address: '上海市浦东新区', phone: '13900139000' },
          // 其他地址
        ],
        defaultAddressId: 1,  // 默认地址ID
        addressForm: {
          id: null,
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
        return this.addresses.slice(start, end);   /* 不会包含end指代的那一项 */
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
          this.addressForm = { id: null, address: '', phone: '' };
          this.addressFormVisible = true;
        }
      },
      saveAddress() {
        if (this.editMode === 'add') {
          const newId = this.addresses.length ? this.addresses[this.addresses.length - 1].id + 1 : 1;
          this.addresses.push({ ...this.addressForm, id: newId });
          /* ...this.addressForm 用于将 this.addressForm 对象的所有属性展开，并与 id: newId 一起合并到一个新的对象中。 */
        } else if (this.editMode === 'edit') {
          const index = this.addresses.findIndex(addr => addr.id === this.addressForm.id);
          if (index !== -1) {
            this.addresses.splice(index, 1, this.addressForm);
            /* index: 要操作的起始位置索引。
              1: 要删除的元素数量。在这个例子中，删除一个元素。
              this.addressForm: 要添加的新元素。在这个例子中，用 this.addressForm 替换被删除的元素。 */
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
  .dialog-footer {
    text-align: right;
  }
  </style>
  