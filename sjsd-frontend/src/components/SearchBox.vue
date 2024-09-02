<template>
    <div class="search">
          <span>
            <i class="el-icon-search" style="font-size: 30px;"></i>
          </span>
          <span>
            <el-autocomplete
            class="inline-input"
            v-model="state2"
            :fetch-suggestions="querySearch"
            placeholder="请输入"
            :trigger-on-focus="false"
            @select="handleSelect"
            ></el-autocomplete>
          </span>
    </div>

</template>

<script>
    export default {
    components: {},
    data() {
      return {
        restaurants: [],
        state1: '',
        state2: '',
        circleUrl:
          "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      };
    },
    methods: {
      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      loadAll() {
        return [
          { "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
        ];
      },
      handleSelect(item) {
        console.log(item);
      }
    },
    mounted() {
      this.restaurants = this.loadAll();
    }
  }


</script>

<style>
.search{

  position: absolute;
  overflow: hidden;
  border-radius: 5px;
  left:40%;
  top: 2%;
  z-index: 120;
}

.inline-input{
  width:200px;
  caret-color: #333;
}

</style>
