<template>
  <div class="container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-input v-model="searchName" placeholder="请填写菜品名称" class="flex-item"></el-input>
      <el-select v-model="selectedCategory" placeholder="请选择分类" class="flex-item" clearable filterable>
          <el-option label="全部" value=""></el-option>
          <!-- 遍历 categoryList 生成 el-option -->
          <el-option
            v-for="category in categoryList"
            :key="category.typeId"
            :label="category.typeName"
            :value="category.typeId"
          ></el-option>
      </el-select>
      <el-select v-model="selectedStatus" placeholder="请选择状态" class="flex-item" clearable>
          <el-option label="全部" value=""></el-option>
          <el-option label="启售" :value="true"></el-option>
          <el-option label="停售" :value="false"></el-option>
      </el-select>
      <!-- 按钮 -->
      <el-button type="danger" @click="deleteSelected">删除</el-button>
      <el-button type="primary" @click="addProduct">新增菜品</el-button>
      <el-button type="success" @click="addCategory">新增分类</el-button>
    </div>

    <!-- 表格 -->
    <el-table
      v-if="categoryList.length > 0 && products.length > 0"
      :data="filteredProducts"
      style="width: 100%;"
      height="550"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" header-align="center"></el-table-column>
      <el-table-column prop="name" label="菜品名称" align="center" header-align="center"></el-table-column>
      <el-table-column label="图片" width="100" align="center" header-align="center">
        <template slot-scope="scope">
          <img :src="scope.row.image" alt="product image" class="product-image">
        </template>
      </el-table-column>
      <el-table-column label="菜品分类" align="center" header-align="center">
        <template slot-scope="scope">
          {{categoryList.find(item => item.typeId === scope.row.typeId).typeName}}
        </template>
      </el-table-column>
      <el-table-column label="售价" width="100" align="center" header-align="center">
        <template slot-scope="scope">
          ￥{{ scope.row.price.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="售卖状态" align="center" header-align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status ? 'success' : 'danger'">
            {{ scope.row.status ? '启售':'停售' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" align="center" header-align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="editProduct(scope.row)">修改</el-button>
          <el-button type="text" size="small" @click="deleteProduct(scope.$index)">删除</el-button>
          <el-button
            type="text"
            size="small"
            :style="{color: scope.row.status ? 'red' : 'green'}"
            @click="toggleStatus(scope.row)"
          >
            {{ scope.row.status ? '停售' : '启售' }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增/修改菜品弹窗 -->
    <el-dialog
      title="菜品管理"
      :visible.sync="add_edit_dialog"
      width="50%"
      @close="resetProductForm"
    >
      <el-form :model="productForm" :rules="rules" ref="productForm" label-width="100px">
        <el-form-item label="菜品名称" prop="name">
          <el-input v-model="productForm.name"></el-input>
        </el-form-item>
        <el-form-item label="菜品分类" prop="typeId">
          <el-select v-model="productForm.typeId" placeholder="请选择分类">
            <el-option
              v-for="category in categoryList"
              :key="category.typeId"
              :label="category.typeName"
              :value="category.typeId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="售价" prop="price">
          <el-input v-model="productForm.price"></el-input>
        </el-form-item>
        <el-form-item label="菜品图片" prop="image">
          <el-input v-model="productForm.image"></el-input>
        </el-form-item>
        <el-form-item label="菜品简介" prop="description">
          <el-input type="textarea" v-model="productForm.description"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="add_edit_dialog = false">取消</el-button>
        <el-button type="primary" @click="submitProductForm">确认</el-button>
      </div>
    </el-dialog>

    <!-- 新增分类弹窗 -->
    <el-dialog
      title="新增分类"
      :visible.sync="addCategoryDialog"
      width="50%"
    >
      <el-input
        placeholder="请输入新分类"
        v-model="addCategoryInput"
        clearable>
      </el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addCategoryDialog = false">取消</el-button>
        <el-button type="primary" @click="submitAddCategory">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import api from '../../api/api.js';

export default {
  data() {
    return {
      searchName: '',
      selectedCategory: '',
      selectedStatus: '',
      selectedProducts: [],
      add_edit_dialog: false,
      addCategoryDialog: false,
      addCategoryInput: '',
      isEdit: false, // 判断是新增还是修改
      productForm: {
        id: null,
        name: '',
        typeId: '',
        price: null,
        image: '',
        description: '',
      },
      rules: {
        name: [{ required: true, message: '请输入菜品名称', trigger: 'blur' }],
        typeId: [{ required: true, message: '请选择菜品分类', trigger: 'change' }],
        price: [{ required: true, message: '请输入菜品价格', trigger: 'blur' }],
        image: [{ required: true, message: '请输入菜品图片地址', trigger: 'blur' }],
        description: [{ required: true, message: '请输入菜品简介', trigger: 'blur' }],
      },
      categoryList:[
        {
          category_:1,
          categoryName:'分类1'
        },
        {
          categoryID:2,
          categoryName:'分类2'
        },
        {
          categoryID:3,
          categoryName:'分类3'
        },
        {
          categoryID:10004,
          categoryName:'分类4'
        },
        {
          categoryID:10005,
          categoryName:'分类5'
        },
      ],

      // categoryList:[],
      products: [],
    };
  },

  async created(){

    try {
      // const categoryResponse = await api.getProType();
      // this.categoryList = categoryResponse.data.data;

      const productResponse = await api.getProductMch();
      this.products = productResponse.data.data;
    } catch (error) {
      console.error('获取数据失败', error);
    }
    try {
        // 使用 await 等待异步请求完成
        const response = await api.getProType();
        this.categoryList = response.data.data; // 将返回的结果赋值给 temp
        } catch (error) {
        console.error('获取商品类型列表失败', error);
        }

  },



  computed: {
    filteredProducts() {
      return this.products.filter(product => {
        return (
          (this.searchName === '' || product.name.includes(this.searchName)) &&
          (this.selectedCategory === '' || product.typeId === this.selectedCategory) &&
          (this.selectedStatus === '' || product.status === this.selectedStatus)
        );
      });
    },
  },
  methods: {
    handleSelectionChange(val) {
      this.selectedProducts = val;
    },
    // 批量删除
    deleteSelected() {
      this.products = this.products.filter(
        product => !this.selectedProducts.includes(product)
      );
    },
    // 单个删除
    deleteProduct(index) {
      this.products.splice(index, 1);
    },
    // 切换状态
    toggleStatus(product) {
      product.status = product.status === true ? false : true;
    },

    addProduct() {
      this.add_edit_dialog = true;
      this.isEdit = false;
    },
    editProduct(product) {
      this.add_edit_dialog = true;
      this.isEdit = true;
      this.productForm = { ...product }; // 赋值修改的产品信息
    },
    addCategory(){
      this.addCategoryDialog = true;
    },
    submitProductForm() {
      this.$refs.productForm.validate((valid) => {
        if (valid) {
          if (this.isEdit) {
            // 编辑产品，找到对应产品并更新
            const index = this.products.findIndex(p => p.id === this.productForm.id);
            if (index !== -1) {
              this.$set(this.products, index, { ...this.productForm });
            }
          } else {
            // 新增产品，创建新的产品ID，并添加到产品列表
            const newProduct = { ...this.productForm, id: this.products.length + 1 };
            this.products.push(newProduct);
          }
          this.add_edit_dialog = false;
          this.resetProductForm(); // 重置表单
        }
      });
    },
    resetProductForm() {
      this.productForm = {
        id: null,
        name: '',
        typeId: '',
        price: null,
        image: '',
        description: '',
      };
    },
    submitAddCategory(){
      // 检查分类列表中是否有重名
      const isDuplicate = this.categoryList.some(category => category.typeName === this.addCategoryInput);
      if (isDuplicate) {
        this.$message.error('分类已存在');
        return;
      }
      // 如果没有重名，可以继续执行添加逻辑
      // api添加
      // ...
      // 刷新categoryList
      // ...
      this.$message.success('分类已添加');
      this.addCategoryDialog = false;
      this.addCategoryInput = '';
    },
  },
};
</script>

<style scoped>
.container {
  padding: 40px;
  padding-top: 20px;
  font-size: 17px;
}

.search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  width: 100%;
}

.flex-item {
  flex: 1;
  margin-right: 10px;
}

.flex-item:last-child {
  margin-right: 0;
  align-items: right;
}

.product-image {
  width: 50px;
  height: 50px;
  object-fit: cover;
}
</style>
