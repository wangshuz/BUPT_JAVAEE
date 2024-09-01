<!-- 用户订单 -->
<!-- "#/clt/order" -->
<template>
    <div>
        <el-table
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
            <template #default="{ row }">
              <div style="font-size: 17px; margin-top: 5px; margin-bottom: 5px; height: 30px;">{{ row.mchname }}</div>
              <div class="scroll-box">
                <!-- 使用 v-for 循环订单信息 -->
                <div id="msgbox" v-for="(order, index) in row.info" :key="index" style="display: flex; align-items: flex-start; margin-right: 20px; background-color: #CECECE; border-radius: 10px;">
                  <!-- 图片部分 -->
                  <span>
                    <img id="msgpicture" class="msgcmp" :src="order[0]" alt="商品图片" style="width: 60px; height: 60px; margin-right: 10px;">
                  </span>
                  <!-- 订单信息部分 -->
                  <span id="msgname" class="msgcmp">{{ order[1] }}</span>
                  <span id="msgprice" class="msgcmp">¥ {{ order[2] }}</span>
                  <span id="msgnum" class="msgcmp">x{{ order[3] }}</span>
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
              <template #default="{ row }">
                <div>
                  <el-button type="text" v-if="row.state === '1'">
                    <span style="color: green;">确认收货</span>
                  </el-button>
                  <el-button type="text" v-if="row.state === '1'">
                    <span style="color: red;">取消订单</span>
                  </el-button>
                  <el-button type="text" v-if="row.state != '1'">
                    <span style="color: green;">再来一单</span>
                  </el-button>
                  <el-button type="text" v-if="row.state != '1'">
                    <span style="color: red">删除订单</span>
                  </el-button>
                  <el-button type="text">
                    <span>查看详情</span>
                  </el-button>
                </div>
              </template>
            </el-table-column>
        </el-table>
        <side-bar :listData="listData" />
    </div>
</template>

<script>
import SideBar from '../SideBar.vue';

  export default {
    components: { SideBar },
    data() {
      return {
        pagestate: '0',
        tableData: [
        // state:  1.待收货  2.已完成  3.已取消
        {
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'2',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'1',
          money:'496'
        },{
          mchname:'火锅小旋转（北京邮电大学学二四楼店）',
          info:[ 
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ],
            [
              'https://th.bing.com/th/id/R.02c432c82120394cfd3d0ee2b68458ff?rik=5fFZd%2bU2AOZITQ&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20190704%2f8720431_212819635000_2.jpg&ehk=lwHXio%2bGcrLhC92opR8DbiIuL0QoQnOkEB4%2fZIWtf6o%3d&risl=&pid=ImgRaw&r=0',
              '六神清爽小火锅',
              '198',
              '1'
            ]
          ],
          date:'2024-08-30 10:30:45',
          state:'3',
          money:'496'
        },],
        listData:[
                {
                    "id":10001,
                    "typeName":"全部",
                    "cb":function(){
                      
                    },
                },
                {
                    "id":10002,
                    "typeName":"待收货",
                    "cb":function(){
                      
                    },
                },
                {
                    "id":10003,
                    "typeName":"已完成",
                    "cb":function(){
                      
                    },
                },
                {
                    "id":10004,
                    "typeName":"已取消",
                    "cb":function(){
                      
                    },
                },
            ]
      }
    },
    methods: {
      rowStyle({ rowIndex }) {
        // 如果行索引是偶数，则返回灰色背景
        if (rowIndex % 2 === 0) {
            return { backgroundColor: '#eeeeee', color: 'black' }; // 浅灰色
        }
        return { color: 'black' }; // 默认样式
      },
      changepagestate(newstate)
      {
        this.data.pagestate = newstate;
        this.filteredData;
      }
    },
    computed: {
        filteredData() {
            let filtered = this.tableData;

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
      margin-left: 100px;
    }
</style>