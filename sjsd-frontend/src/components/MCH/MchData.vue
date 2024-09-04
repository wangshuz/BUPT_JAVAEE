<template>
  <div class="stats-dashboard">
    <!-- 时间范围选择 -->
    <el-radio-group v-model="selectedRange" @change="updateData">
      <el-radio-button label="近七天"></el-radio-button>
      <el-radio-button label="近三十天"></el-radio-button>
      <el-radio-button label="近一年"></el-radio-button>
    </el-radio-group>

    <!-- 统计信息 -->
    <div class="stats-cards">
      <div class="stat-card">
        <div class="stat-info">
          <h3>营业额</h3>
          <p>{{ salesData.total }}元</p>
        </div>
        <div class="stat-chart" id="lineChart1"></div>
      </div>

      <div class="stat-card">
        <div class="stat-info">
          <h3>订单统计</h3>
          <p>总订单：{{ orderData.total }}</p>
          <p>有效订单：{{ orderData.valid }}</p>
          <p>无效订单：{{ orderData.invalid }}</p>
        </div>
        <div class="stat-chart" id="lineChart2"></div>
      </div>

      <div class="stat-card">
        <div class="stat-info">
          <h3>商品销量</h3>
        </div>
        <div class="stat-chart" id="barChart1"></div>
      </div>

      <div class="stat-card">
        <div class="stat-info">
          <h3>顾客数量</h3>
          <p>顾客总数：{{ customerData.total }}</p>
          <p>新增顾客：{{ customerData.new }}</p>
        </div>
        <div class="stat-chart" id="lineChart3"></div>
      </div>
    </div>
  </div>
</template>


<script setup>
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
</script>

<script>
export default {
  data() {
    return {
      selectedRange: '近七天',
      salesData: {},
      orderData: {},
      productSalesData: {},
      customerData: {},
      xAxisData: [], // 用于存储横坐标标签
      xAxisLabel: '', // 横坐标的标签
    };
  },
  methods: {
    updateData() {
      switch (this.selectedRange) {
        case '近七天':
          this.xAxisLabel = '天';
          this.setDataForSevenDays();
          break;
        case '近三十天':
          this.xAxisLabel = '天';
          this.setDataForThirtyDays();
          break;
        case '近一年':
          this.xAxisLabel = '月';
          this.setDataForYear();
          break;
      }
    },
    setDataForSevenDays() {
      this.xAxisData = Array.from({ length: 7 }, (_, i) => (i + 1).toString()); // 生成 1 到 7 的数组

      this.salesData = {
        total: 1342.9,
        chartData: [120, 140, 150, 130, 160, 180, 170]
      };
      this.orderData = {
        total: 158,
        valid: 130,
        invalid: 28,
        chartData: [100, 120, 130, 110, 150, 160, 140]
      };
      this.productSalesData = {
        chartData: [50, 80, 90, 70, 60]
      };
      this.customerData = {
        total: 139,
        new: 18,
        chartData: [100, 120, 130, 110, 150, 160, 140]
      };

      this.initCharts();
    },
    setDataForThirtyDays() {
      this.xAxisData = Array.from({ length: 30 }, (_, i) => (i + 1).toString()); // 生成 1 到 30 的数组

      this.salesData = {
        total: 7429.6,
        chartData: [100, 200, 300, 150, 200, 250, 300]
      };
      this.orderData = {
        total: 1058,
        valid: 830,
        invalid: 228,
        chartData: [100, 200, 250, 150, 200, 180, 220]
      };
      this.productSalesData = {
        chartData: [150, 180, 190, 170, 160]
      };
      this.customerData = {
        total: 839,
        new: 118,
        chartData: [100, 200, 150, 150, 180, 200, 180]
      };

      this.initCharts();
    },
    setDataForYear() {
      this.xAxisData = Array.from({ length: 12 }, (_, i) => (i + 1).toString()); // 生成 1 到 12 的数组

      this.salesData = {
        total: 13429.6,
        chartData: [1200, 1400, 1500, 1300, 1600, 1800, 1700, 1600, 1400, 1300, 1200, 1100]
      };
      this.orderData = {
        total: 1580,
        valid: 1300,
        invalid: 280,
        chartData: [1000, 1200, 1300, 1100, 1500, 1600, 1400, 1500, 1600, 1400, 1300, 1200]
      };
      this.productSalesData = {
        chartData: [1500, 1800, 1900, 1700, 1600, 1500, 1400, 1300, 1200, 1100, 1000, 900]
      };
      this.customerData = {
        total: 1839,
        new: 118,
        chartData: [1000, 1200, 1300, 1100, 1500, 1600, 1400, 1500, 1600, 1400, 1300, 1200]
      };

      this.initCharts();
    },
    initCharts() {
      this.initLineChart1();
      this.initLineChart2();
      this.initBarChart1();
      this.initLineChart3();
    },
    initLineChart1() {
      var lineChart1 = echarts.init(document.getElementById('lineChart1'));  //把id是lineChart1的元素作为图表的容器
      lineChart1.setOption({
        title: {
          text: ''
        },
        tooltip: {    //设置提示框的行为和样式。当鼠标放在坐标轴上时，图表上的时候显示提示框，当鼠标离开的时候隐藏提示框
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',   //表示 X 轴是类别轴
          data: this.xAxisData, // 使用动态生成的横坐标标签
          name: this.xAxisLabel, // 设置横坐标标签
          nameLocation: 'end'   // 表示 X 轴名称显示在轴的末端
        },
        yAxis: {
          type: 'value',//表示 Y 轴是数值轴
          name: '元', // 设置纵坐标标签
          nameLocation: 'end'
        },
        series: [
          {
            data: this.salesData.chartData,
            type: 'line'
          }
        ]
      });
    },
    initLineChart2() {
      var lineChart2 = echarts.init(document.getElementById('lineChart2'));
      lineChart2.setOption({
        title: {
          text: ''
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: this.xAxisData,
          name: this.xAxisLabel, // 设置横坐标标签
          nameLocation: 'end'
        },
        yAxis: {
          type: 'value',
          name: '单', // 设置纵坐标标签
          nameLocation: 'end'
        },
        series: [
          {
            data: this.orderData.chartData,
            type: 'line'
          }
        ]
      });
    },
    initBarChart1() {
      var barChart1 = echarts.init(document.getElementById('barChart1'));
      barChart1.setOption({
        title: {
          text: ''
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        xAxis: {
          type: 'category',
          data: this.xAxisData,
          name: this.xAxisLabel, // 设置横坐标标签
          nameLocation: 'end'
        },
        yAxis: {
          type: 'value',
          name: '个', // 设置纵坐标标签
          nameLocation: 'end'
        },
        series: [
          {
            data: this.productSalesData.chartData,
            type: 'bar'
          }
        ]
      });
    },
    initLineChart3() {
      var lineChart3 = echarts.init(document.getElementById('lineChart3'));
      lineChart3.setOption({
        title: {
          text: ''
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: this.xAxisData,
          name: this.xAxisLabel, // 设置横坐标标签
          nameLocation: 'end'
        },
        yAxis: {
          type: 'value',
          name: '人数', // 设置纵坐标标签
          nameLocation: 'end'
        },
        series: [
          {
            data: this.customerData.chartData,
            type: 'line'
          }
        ]
      });
    }
  },
  mounted() {
    this.updateData(); // 初始化时更新数据并绘制图表
  }
};
</script>

<style scoped>
.stats-cards {
  display: flex;
  flex-wrap: wrap;  /* flex-wrap: wrap; 允许子元素在必要时换行。如果子元素在一行中无法完全显示，它们将换到下一行。 */
  justify-content: space-between;  /* justify-content: space-between; 将子元素在主轴（通常是水平轴）上均匀分布，第一个子元素与容器的起点对齐，最后一个子元素与容器的终点对齐，其他子元素在中间均匀分布。 */
}

.stat-card {
  display: flex;
  justify-content: space-between;
  align-items: center;   /* align-items: center; 将子元素在交叉轴（通常是垂直轴）上居中对齐 */
  width: 40%;   /* width: 45%; 将元素的宽度设置为其父容器宽度的 45%。 */
  padding: 10px;    /* padding: 10px; 为元素的内边距设置为 10 像素。这意味着内容与元素边框之间会有 10 像素的间距。 */
  margin-bottom: 20px;   /* margin-bottom: 20px; 为元素的底部外边距设置为 20 像素。这意味着元素与其下方的其他元素之间会有 20 像素的间距。 */
  height: 350px; /* 统一高度 */
  margin-left: 4%;
  margin-right: 4%;
}

.stat-info {
  display: flex;
  flex-direction: column;
  flex: 1;
  padding-right: 20px; /* 文字和图表之间的间距 */
  position: relative; /* 设置父元素为相对定位 */
}
.stat-chart {
  flex: 2;
  height: 100%; /* 确保图表填满容器的高度 */
}

</style>
