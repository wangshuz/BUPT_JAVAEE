<template>
    <div class="container">
      <!-- 搜索栏 -->
      <div class="search-bar">
        <el-input v-model="searchName" placeholder="请填写菜品名称" class="flex-item"></el-input>
        <el-select v-model="selectedCategory" placeholder="请选择分类" class="flex-item" clearable>
            <el-option label="所有分类" value=""></el-option>
            <el-option label="分类1" value="分类1"></el-option>
            <el-option label="分类2" value="分类2"></el-option>
        </el-select>
        <el-select v-model="selectedStatus" placeholder="请选择状态" class="flex-item" clearable>
            <el-option label="所有状态" value=""></el-option>
            <el-option label="启售" value="启售"></el-option>
            <el-option label="停售" value="停售"></el-option>
        </el-select>
        <div class="flex-item">
            <el-button type="danger" @click="deleteSelected">删除</el-button>
            <el-button type="primary" @click="addProduct">新增菜品</el-button>
        </div>
      </div>

      <!-- 表格 -->
      <el-table
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
        <el-table-column prop="category" label="菜品分类" align="center" header-align="center"></el-table-column>
        <el-table-column prop="price" label="售价" width="100" align="center" header-align="center"></el-table-column>
        <el-table-column prop="status" label="售卖状态" align="center" header-align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status === '启售' ? 'success' : 'danger'">
              {{ scope.row.status }}
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
              :style="{color: scope.row.status === '启售' ? 'red' : 'green'}"
              @click="toggleStatus(scope.row)"
            >
              {{ scope.row.status === '启售' ? '停售' : '启售' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
export default {
  data() {
    return {
      searchName: '',
      selectedCategory: '',
      selectedStatus: '',
      selectedProducts: [],
      products: [
        // 模拟数据
        {
            name: '霸王龙',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '启售',
          },
          {
            name: '汉堡包',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '停售',
          },
          {
            name: '张钧渭',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类2',
            price: '¥1',
            status: '启售',
          },
          {
            name: '高志强',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '启售',
          },
          {
            name: '谢又新',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类2',
            price: '¥1',
            status: '启售',
          },
          {
            name: '李欣妍',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类2',
            price: '¥1',
            status: '启售',
          },
          {
            name: '熊瑞',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥9999',
            status: '启售',
          },
          {
            name: '霸王龙',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '启售',
          },
          {
            name: '汉堡包',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '停售',
          },{
            name: '霸王龙',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '启售',
          },
          {
            name: '汉堡包',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '停售',
          },{
            name: '霸王龙',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '启售',
          },
          {
            name: '汉堡包',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '停售',
          },{
            name: '霸王龙',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '启售',
          },
          {
            name: '汉堡包',
            image: 'https://mlady.2liang.net/d/file/diet/xican/2016-07/a8155741c4044c99261ca963c1800922.jpg',
            category: '分类1',
            price: '¥1',
            status: '停售',
          },
      ],
    };
  },
  computed: {
    filteredProducts() {
      return this.products.filter(product => {
        return (
          (this.searchName === '' || product.name.includes(this.searchName)) &&
          (this.selectedCategory === '' || product.category === this.selectedCategory) &&
          (this.selectedStatus === '' || product.status === this.selectedStatus)
        );
      });
    },
  },
  methods: {
    handleSelectionChange(val) {
      this.selectedProducts = val;
    },
    deleteSelected() {
      this.products = this.products.filter(
        product => !this.selectedProducts.includes(product)
      );
    },
    addProduct() {
      // 逻辑来新增产品
    },
    editProduct(product) {
      // 逻辑来编辑产品
    },
    deleteProduct(index) {
      this.products.splice(index, 1);
    },
    toggleStatus(product) {
      product.status = product.status === '启售' ? '停售' : '启售';
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
}

.flex-item {
  flex: 1;
  margin-right: 10px;
}

.flex-item:last-child {
  margin-right: 0;
}


.product-image {
  width: 50px;
  height: 50px;
  object-fit: cover;
}
</style>
