<!-- 用户订单 -->
<!-- "#/clt/order" -->
<template>
    <div id="container">

      <div id="timeselecter">
        <span>筛选日期：</span>
        <el-date-picker
          v-model="date"
          size="small"
          clearable
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions">
        </el-date-picker>
      </div>
      
        <el-table
            id="table"
            :data="filteredData"
            stripe
            style="width: 100%"
            height="800px"
            :row-style="rowStyle">
            <!-- 占位列 -->
            <el-table-column width="250"></el-table-column>
            <el-table-column
            label="订单信息"
            width="700">
            <template  slot-scope="scope">
              <div style="font-size: 17px; margin-top: 5px; margin-bottom: 5px; height: 30px;"  @click="goToStore(scope.$index)">{{ scope.row.mchname }}</div>
              <div class="scroll-box">
                <!-- 使用 v-for 循环订单信息 -->
                <div id="msgbox" v-for="(order, index) in scope.row.cltOrderInfoList" :key="index" style="display: flex; align-items: flex-start; margin-right: 20px; background-color: #CECECE; border-radius: 10px;">
                  <!-- 图片部分 -->
                  <span>
                    <img id="msgpicture" class="msgcmp" :src="order.picture" alt="商品图片" style="width: 60px; height: 60px; margin-right: 10px;">
                  </span>
                  <!-- 订单信息部分 -->
                  <span id="msgname" class="msgcmp">{{ order.name }}</span>
                  <span id="msgprice" class="msgcmp">¥ {{ order.price }}</span>
                  <span id="msgnum" class="msgcmp">x{{ order.num }}</span>
                </div>
              </div>
            </template>
            </el-table-column>
            <el-table-column
            prop="date"
            label="下单时间"
            width="160">
            </el-table-column>
            <el-table-column
            label="订单状态"  align="center" width="120">
              <template #default="{ row }">
                <div v-if="row.state === '1'">待收货</div>
                <div v-if="row.state === '2'">已完成</div>
                <div v-if="row.state === '3'">已取消</div>
              </template>
            </el-table-column>
            <el-table-column
            label="金额"  align="center" width="120">
              <template #default="{ row }">
                <div>¥ {{ row.money }}</div>
              </template>
            </el-table-column>
            <el-table-column
            label="操作">
              <template slot-scope="scope">
                <el-button type="text" v-if="scope.row.state === '1'" @click="confirmreceipt(scope.$index)">
                    <span style="color: green;">确认收货</span>
                  </el-button>
                  <el-button type="text" v-if="scope.row.state === '1'" @click="cancleorder(scope.$index)">
                    <span style="color: red;">取消订单</span>
                  </el-button>
                  <el-button type="text" v-if="scope.row.state != '1'" @click="goToStore(scope.$index)">
                    <span style="color: green;">再来一单</span>
                  </el-button>
                  <el-button type="text" v-if="scope.row.state != '1'" @click="deleteOrder(scope.$index)">
                    <span style="color: red">删除订单</span>
                  </el-button >
                  <el-button type="text" @click="goToOrder(scope.$index)">
                    <span>查看详情</span>
                  </el-button>
              </template>
            </el-table-column>
        </el-table>
        
        <side-bar :listData="listData" @change-pagestate="changepagestate" />
        <search-box :childContent.sync='content' />
    </div>
</template>

<script>
import SideBar from '../SideBar.vue';
import SearchBox from '../SearchBox.vue';
import { time } from 'echarts';
import api from '../../api/api.js';

  export default {
    components: { SideBar,SearchBox },
    data() {
      return {
        userId:'',
        content:'',
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        date: '',
        pagestate: '0',
        tableData: [
        // state:  1.待收货  2.已完成  3.已取消
        {
          mchId:'1',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
        {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchId:'',
          orderId:'',
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          cltOrderInfoList:[ 
          {
            picture: 'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
            name: '六神清爽小火锅',
            price: '198',
            num: '2'
        },
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },],
        listData:[
                {
                    "id":0,
                    "typeName":"全部",
                    "cb":function(){
                      this.$emit('change-pagestate','0');
                      
                    },
                },
                {
                    "id":'10002',
                    "typeName":"待收货",
                    "cb":function(){
                      this.$emit('change-pagestate','1');
                    },
                },
                {
                    "id":'10003',
                    "typeName":"已完成",
                    "cb":function(){
                      this.$emit('change-pagestate','2');
                    },
                },
                {
                    "id":'10004',
                    "typeName":"已取消",
                    "cb":function(){
                      this.$emit('change-pagestate','3');
                    },
                },
            ]
      }
    },
    methods: {
      async fetchOrders() {
          //getCltOrders(userId){
          //    return apiClient.get(`/api/getCltOrders?userId=${userId}`);
          //},
          try{
            const response = await api.getCltOrders();
            this.tableData = response.data.data;
          }
          catch(error)
          {
            console.error('获取订单列表时出错:', error);
          }
          // api.getCltOrders(this.userId)
          // .then(response => {
          // this.tableData = response.data.data;
          // })
          // .catch(error => {
          // console.error('获取订单列表时出错:', error);
          // });
      },
      async changeCltOrderRealtime(orderId){
        //changeCltOrderRealtime(orderId, realtime){
          //    return apiClient.get(`/api/changeCltOrderRealtime?orderId=${orderId}&realtime=${realtime}`);
          //},
          try{
            const response = await api.changeCltOrderRealtime(orderId, this.getCurrentTime());
            console.log(response.data);
            await this.fetchOrders(); 
          }
          catch(error)
          {
            console.error('更新送达时间时出错:', error);
          }
          // const now = new Date();
          // api.changeCltOrderRealtime(orderId, now.toString())
          // .then(response => {
          // console.log(response.data);
          // this.fetchCltOrders(); // 更新状态后重新获取订单列表
          // })
          // .catch(error => {
          // console.error('更新送达时间时出错:', error);
          // });
      },
      async changeCltOrderStatus(orderId, status) {
          //updateCltOrderStatus(orderId, status){
          //    return apiClient.get(`/api/updateCltOrderStatus?orderId=${orderId}&status=${status}`);
          //},
          try{
            const response = await api.updateCltOrderStatus(orderId, status);
            console.log(response.data);
            await this.fetchOrders(); // 更新状态后重新获取订单列表
          }
          catch(error)
          {
            console.error('更新订单状态时出错:', error);
          }
          // api.updateCltOrderStatus(orderId, status)
          // .then(response => {
          // console.log(response.data);
          // this.fetchCltOrders(); // 更新状态后重新获取订单列表
          // })
          // .catch(error => {
          // console.error('更新订单状态时出错:', error);
          // });
      },
      async deleteCltOrder(orderId) {
          //deleteCltOrder(orderId){
          //    return apiClient.get(`/api/deleteCltOrder?orderId=${orderId}`);
          //},
          try{
            const response = await api.deleteCltOrder(orderId);
            console.log(response.data);
            await this.fetchOrders(); // 删除订单后重新获取订单列表
          }
          catch(error)
          {
            console.error('删除订单时出错:', error);
          }
          // api.deleteCltOrder(orderId)
          // .then(response => {
          // console.log(response.data);
          // this.fetchCltOrders(); // 删除订单后重新获取订单列表
          // })
          // .catch(error => {
          // console.error('删除订单时出错:', error);
          // });
      },
      handleDateChange() {
          this.filteredData();
      },
      rowStyle({ rowIndex }) {
        // 如果行索引是偶数，则返回灰色背景
        if (rowIndex % 2 === 0) {
            return { backgroundColor: '#eeeeee', color: 'black' }; // 浅灰色
        }
        return { color: 'black' }; // 默认样式
      },
      changepagestate(newstate)
      {
        this.pagestate = newstate;
        this.filteredData();
      },
      goToStore(index) {
         // 如果当前标签页是“待接单”
         if (this.pagestate === '1') {  // 假设“待接单”标签页的 name 是 'second'
        // 计算在“待接单”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '1') {
                if (count === index) {
                    index = i;
                    break;
                }
                count++;
                }
            }
        }
        // 如果当前标签页是“待派送”
        else if (this.pagestate === '2') {  // 假设“待派送”标签页的 name 是 'third'
        // 计算在“待派送”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '2') {
                if (count === index) {
                  index = i;
                    break;
                }
                count++;
                }
            }
        }
        // 如果当前标签页是“派送中”
        else if (this.pagestate === '3') {  // 假设“派送中”标签页的 name 是 'fourth'
        // 计算在“派送中”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '3') {
                if (count === index) {
                  index = i;
                    break;
                }
                count++;
                }
            }
        }
        this.$router.push({ 
          name: 'CltStore',
          query: { mchId : this.tableData[index].mchId }  // 传递数据
         }); // 通过路由的名称跳转
      },
      goToOrder(index) {
        // 如果当前标签页是“待接单”
        if (this.pagestate === '1') {  // 假设“待接单”标签页的 name 是 'second'
        // 计算在“待接单”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '1') {
                if (count === index) {
                    index = i;
                    break;
                }
                count++;
                }
            }
        }
        // 如果当前标签页是“待派送”
        else if (this.pagestate === '2') {  // 假设“待派送”标签页的 name 是 'third'
        // 计算在“待派送”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '2') {
                if (count === index) {
                  index = i;
                    break;
                }
                count++;
                }
            }
        }
        // 如果当前标签页是“派送中”
        else if (this.pagestate === '3') {  // 假设“派送中”标签页的 name 是 'fourth'
        // 计算在“派送中”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '3') {
                if (count === index) {
                  index = i;
                    break;
                }
                count++;
                }
            }
        }
        this.$router.push({ 
          name: 'CltOrderDetail' ,
          query: { orderId : this.tableData[index].orderId }  // 传递数据
        }); // 通过路由的名称跳转
      },
      async confirmreceipt(index) {
        // 如果当前标签页是“全部订单”
        if (this.pagestate === '0') {
        //this.tableData[index].state = '2';
        await this.changeCltOrderRealtime(this.tableData[index].orderId);
        await this.changeCltOrderStatus(this.tableData[index].orderId, '4') 
        } 
        // 如果当前标签页是“待接单”
        else if (this.pagestate === '1') {  // 假设“待接单”标签页的 name 是 'second'
        // 计算在“待接单”标签页中索引对应的订单
        let count = 0;
        for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].state === '1') {
            if (count === index) {
                //this.tableData[i].state = '2';
                await this.changeCltOrderRealtime(this.tableData[i].orderId);
                await this.changeCltOrderStatus(this.tableData[i].orderId, '4') 
                break;
            }
            count++;
            }
        }
        }
      },
      async cancleorder(index) {
          // 如果当前标签页是“全部订单”
          if (this.pagestate === '0') {
          //this.tableData[index].state = '3';
          await this.changeCltOrderStatus(this.tableData[index].orderId, '5');
          } 
          // 如果当前标签页是“待接单”
          else if (this.pagestate === '1') {  // 假设“待接单”标签页的 name 是 'second'
          // 计算在“待接单”标签页中索引对应的订单
          let count = 0;
          for (let i = 0; i < this.tableData.length; i++) {
              if (this.tableData[i].state === '1') {
              if (count === index) {
                  //this.tableData[i].state = '3';
                  await this.changeCltOrderStatus(this.tableData[i].orderId, '5'); 
                  break;
              }
              count++;
              }
          }
          }
      },
      async deleteOrder(index) {
        // 如果当前标签页是“全部订单”
        if (this.pagestate === '0') {
            //this.tableData.splice(index, 1);
            await this.deleteCltOrder(this.tableData[index].orderId);
        } 
        // 如果当前标签页是“待接单”
        else if (this.pagestate === '1') {  // 假设“待接单”标签页的 name 是 'second'
        // 计算在“待接单”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '1') {
                if (count === index) {
                    //this.tableData.splice(i, 1);
                    await this.deleteCltOrder(this.tableData[i].orderId);
                    break;
                }
                count++;
                }
            }
        }
        // 如果当前标签页是“待派送”
        else if (this.pagestate === '2') {  // 假设“待派送”标签页的 name 是 'third'
        // 计算在“待派送”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '2') {
                if (count === index) {
                    //this.tableData.splice(i, 1);
                    await this.deleteCltOrder(this.tableData[i].orderId);
                    break;
                }
                count++;
                }
            }
        }
        // 如果当前标签页是“派送中”
        else if (this.pagestate === '3') {  // 假设“派送中”标签页的 name 是 'fourth'
        // 计算在“派送中”标签页中索引对应的订单
            let count = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].state === '3') {
                if (count === index) {
                    //this.tableData.splice(i, 1);
                    await this.deleteCltOrder(this.tableData[i].orderId);
                    break;
                }
                count++;
                }
            }
        }
    },
    handlecontentchange()
    {
      this.filteredData();
    },
    getCurrentTime() {
      const now = new Date();
      
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要+1
      const day = String(now.getDate()).padStart(2, '0');
      
      const hours = String(now.getHours()).padStart(2, '0');
      const minutes = String(now.getMinutes()).padStart(2, '0');
      const seconds = String(now.getSeconds()).padStart(2, '0');
      
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
    },
    watch:{
      content(newValue, oldValue)
      {
        this.handlecontentchange();
      }
    },
    mounted(){
      // fetchOrders();
    },
    async created()
    {
      await this.fetchOrders();
      // 每隔5秒钟刷新数据
      console.log('数据获取完成，启动定时器');
                this.interval = setInterval(async () => {
                    console.log('定时器触发'); // 确认定时器是否被触发
                this.currentTime = new Date().toString();
                console.log(this.getCurrentTime());
                await this.fetchOrders();
                }, 3000); // 5000 毫秒 = 5秒
    },
    beforeDestroy() {
            // 销毁组件前清除定时器，防止内存泄漏
            clearInterval(this.interval);
        },
    computed: {
        filteredData() {
            let filtered = this.tableData;

            // 按商家名搜索
            if (this.content) {
              filtered = filtered.filter(item =>
                  item.mchname.includes(this.content)
              );
            }

            // 按时间范围筛选
            if (this.date.length === 2) {
                    const [startDate, endDate] = this.date;
                    filtered = filtered.filter(item => {
                        const date = new Date(item.date);
                        return date >= startDate && date <= endDate;
                    });
                }

            // 按标签页状态筛选
            if (this.pagestate !== '0') {
                filtered = filtered.filter(item =>
                    item.state === this.pagestate
                );
            }

            return filtered;
        }
    }
  }
</script>

<style scoped>
    ::v-deep .odd-row {
      background-color: #000; /* 替换成你想要的颜色 */
    }
    .scroll-box {
      display: flex;           /* 使子元素水平排列 */
      overflow-x: auto;        /* 横向滚动条 */
      white-space: nowrap;     /* 防止子元素换行 */
    }
    .msgcmp {
      position: relative;
      /* display: flex;
      align-items: flex-start; */
    }
    #msgbox {
      border: 1px solid #000;
      padding: 0px; 
      min-width: 250px;
      max-width: 250px;
      height: 80px;
    }
    #msgpicture {
      height: 60px;
      width: 60px;
      margin-top: 9px;
      margin-left: 9px;
      
    }
    #msgname {
      white-space: normal;
      min-width: 160px;
      max-width: 160px;
      /* border: 1px solid #000; */
      margin-top: 9px;
    }
    #msgprice {
      margin-top: 50px;
      margin-left: -160px;
    }
    #msgnum {
      margin-top: 50px;
      margin-left: 80px;
    }
    #timeselecter {
      position:absolute;
      left: 450px;
      top: 80px;
      z-index: 2;
    }
</style>