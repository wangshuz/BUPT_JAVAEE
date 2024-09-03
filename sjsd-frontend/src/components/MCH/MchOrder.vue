<!-- 商家订单管理 -->
<!-- #/mch/order -->
<template>
    <div class="container">
        <span class="timeselecter">
            <span style="font-size: 17px;">筛选时间：</span>
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
        </span>
        <span class="searcher">
            <el-input
                size="small"
                clearable
                v-model="input"
                placeholder="请输入用户名"
                @input="handleSearch"
            >
            </el-input>
        </span>
        <span>
            <el-tabs class="tab" v-model="activeName" @tab-click="handleTabClick">
                <el-tab-pane class="tabtext" label="全部订单" name="first">
                    <el-table
                        :data="filteredData"
                        style="width: 100%"
                        height="750px"
                        :row-style="rowStyle">
                        <el-table-column prop="orderID" label="订单号" width="220"></el-table-column>
                        <el-table-column prop="username" label="用户名" width="140"></el-table-column>
                        <el-table-column prop="phonenumber" label="手机号" width="160"></el-table-column>
                        <el-table-column prop="location" label="地址" width="290"></el-table-column>
                        <el-table-column prop="ordertime" label="下单时间" width="120">
                            <template v-slot="scope">
                                {{ scope.row.ordertime.split(' ')[1] }}
                            </template>
                        </el-table-column>
                        <el-table-column label="实收金额" width="140" align="center">
                            <template v-slot="scope">
                                <div>
                                    ¥ {{ scope.row.money }}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="orderstate" label="订单状态" width="100" align="center">
                            <template v-slot="scope">
                                <span v-if="scope.row.orderstate === '1'">待接单</span>
                                <span v-else-if="scope.row.orderstate === '2'">待派送</span>
                                <span v-else-if="scope.row.orderstate === '3'">派送中</span>
                                <span v-else-if="scope.row.orderstate === '4'">已完成</span>
                                <span v-else-if="scope.row.orderstate === '5'">已取消</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation" label="操作" width="200" align="right">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" v-if="scope.row.orderstate === '1'" @click="acceptOrder(scope.$index)">
                                    <span style="color: green;">接单</span>
                                </el-button>
                                <el-button type="text" size="small" v-if="scope.row.orderstate === '1'" @click="rejectOrder(scope.$index)">
                                    <span style="color: red;">拒单</span>
                                </el-button>
                                <el-button type="text" size="small" v-if="scope.row.orderstate === '2'" @click="cancelOrder(scope.$index)">
                                    <span style="color: red;">取消</span>
                                </el-button>
                                <el-button type="text" size="small" @click="handleopen(scope.$index)">查看</el-button>
                                <el-button type="text" size="small" @click="deleteOrder(scope.$index)">
                                    <span style="color: red;">删除订单</span>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane class="tabtext" label="待接单" name="second">
                    <el-table
                        :data="filteredData"
                        stripe
                        style="width: 100%"
                        height="750px"
                        :row-style="rowStyle">
                        <el-table-column prop="orderID" label="订单号" width="220"></el-table-column>
                        <el-table-column prop="username" label="用户名" width="140"></el-table-column>
                        <el-table-column prop="phonenumber" label="手机号" width="160"></el-table-column>
                        <el-table-column prop="location" label="地址" width="290"></el-table-column>
                        <el-table-column prop="ordertime" label="下单时间" width="120">
                            <template v-slot="scope">
                                {{ scope.row.ordertime.split(' ')[1] }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="money" label="实收金额" width="140" align="center"></el-table-column>
                        <el-table-column prop="orderstate" label="订单状态" width="100" align="center">
                            <template v-slot="scope">
                                <span v-if="scope.row.orderstate === '1'">待接单</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation" label="操作" width="200" align="right">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" v-if="scope.row.orderstate === '1'" @click="acceptOrder(scope.$index)">
                                    <span style="color: green;">接单</span>
                                </el-button>
                                <el-button type="text" size="small" v-if="scope.row.orderstate === '1'" @click="rejectOrder(scope.$index)">
                                    <span style="color: red;">拒单</span>
                                </el-button>
                                <el-button type="text" size="small" @click="handleopen(scope.$index)">查看</el-button>
                                <el-button type="text" size="small" @click="deleteOrder(scope.$index)">
                                    <span style="color: red;">删除订单</span>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane class="tabtext" label="待派送" name="third">
                    <el-table
                        :data="filteredData"
                        stripe
                        style="width: 100%"
                        height="750px"
                        :row-style="rowStyle">
                        <el-table-column prop="orderID" label="订单号" width="220"></el-table-column>
                        <el-table-column prop="username" label="用户名" width="140"></el-table-column>
                        <el-table-column prop="phonenumber" label="手机号" width="160"></el-table-column>
                        <el-table-column prop="location" label="地址" width="290"></el-table-column>
                        <el-table-column prop="ordertime" label="下单时间" width="120">
                            <template v-slot="scope">
                                {{ scope.row.ordertime.split(' ')[1] }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="money" label="实收金额" width="140" align="center"></el-table-column>
                        <el-table-column prop="orderstate" label="订单状态" width="100" align="center">
                            <template v-slot="scope">
                                <span v-if="scope.row.orderstate === '2'">待派送</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation" label="操作" width="200" align="right">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" v-if="scope.row.orderstate === '2'" @click="cancelOrder(scope.$index)">
                                    <span style="color: red;">取消</span>
                                </el-button>
                                <el-button type="text" size="small" @click="handleopen(scope.$index)">查看</el-button>
                                <el-button type="text" size="small" @click="deleteOrder(scope.$index)">
                                    <span style="color: red;">删除订单</span>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane class="tabtext" label="派送中" name="fourth">
                    <el-table
                        :data="filteredData"
                        stripe
                        style="width: 100%"
                        height="750px"
                        :row-style="rowStyle">
                        <el-table-column prop="orderID" label="订单号" width="220"></el-table-column>
                        <el-table-column prop="username" label="用户名" width="140"></el-table-column>
                        <el-table-column prop="phonenumber" label="手机号" width="160"></el-table-column>
                        <el-table-column prop="location" label="地址" width="290"></el-table-column>
                        <el-table-column prop="ordertime" label="下单时间" width="120">
                            <template v-slot="scope">
                                {{ scope.row.ordertime.split(' ')[1] }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="money" label="实收金额" width="140" align="center"></el-table-column>
                        <el-table-column prop="orderstate" label="订单状态" width="100" align="center">
                            <template v-slot="scope">
                                <span v-if="scope.row.orderstate === '3'">派送中</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation" label="操作" width="200" align="right">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" @click="handleopen(scope.$index)">查看</el-button>
                                <el-button type="text" size="small" @click="deleteOrder(scope.$index)">
                                    <span style="color: red;">删除订单</span>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane class="tabtext" label="已完成" name="fifth">
                    <el-table
                        :data="filteredData"
                        stripe
                        style="width: 100%"
                        height="750px"
                        :row-style="rowStyle">
                        <el-table-column prop="orderID" label="订单号" width="220"></el-table-column>
                        <el-table-column prop="username" label="用户名" width="140"></el-table-column>
                        <el-table-column prop="phonenumber" label="手机号" width="160"></el-table-column>
                        <el-table-column prop="location" label="地址" width="290"></el-table-column>
                        <el-table-column prop="ordertime" label="下单时间" width="120">
                            <template v-slot="scope">
                                {{ scope.row.ordertime.split(' ')[1] }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="money" label="实收金额" width="140" align="center"></el-table-column>
                        <el-table-column prop="orderstate" label="订单状态" width="100" align="center">
                            <template v-slot="scope">
                                <span v-if="scope.row.orderstate === '4'">已完成</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation" label="操作" width="200" align="right">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" v-if="scope.row.orderstate === '4'" @click="handleopen(scope.$index)">查看</el-button>
                                <el-button type="text" size="small" @click="deleteOrder(scope.$index)">
                                    <span style="color: red;">删除订单</span>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane class="tabtext" label="已取消" name="sixth">
                    <el-table
                        :data="filteredData"
                        stripe
                        style="width: 100%"
                        height="750px"
                        :row-style="rowStyle">
                        <el-table-column prop="orderID" label="订单号" width="220"></el-table-column>
                        <el-table-column prop="username" label="用户名" width="140"></el-table-column>
                        <el-table-column prop="phonenumber" label="手机号" width="160"></el-table-column>
                        <el-table-column prop="location" label="地址" width="290"></el-table-column>
                        <el-table-column prop="ordertime" label="下单时间" width="120">
                            <template v-slot="scope">
                                {{ scope.row.ordertime.split(' ')[1] }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="money" label="实收金额" width="140" align="center"></el-table-column>
                        <el-table-column prop="orderstate" label="订单状态" width="100" align="center">
                            <template v-slot="scope">
                                <span v-if="scope.row.orderstate === '5'">已取消</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation" label="操作" width="200" align="right">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" @click="handleopen(scope.$index)">查看</el-button>
                                <el-button type="text" size="small" @click="deleteOrder(scope.$index)">
                                    <span style="color: red;">删除订单</span>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>
        </span>
        <el-dialog
            title="订单详情"
            :visible.sync="dialogVisible"
            width="50%">
            <div style="color: black; font-size: 20px;">
                <div style="font-size: 30px;">
                    <span>订单号：{{ dialogitem.orderID }}</span>
                    <span style="margin-left: 10%;">
                        <span>订单状态：</span>
                        <span v-if="dialogitem.orderstate === '1'" style="color: red;">待接单</span>
                        <span v-if="dialogitem.orderstate === '2'" style="color: orange">待派送</span>
                        <span v-if="dialogitem.orderstate === '3'" style="color: orange;">派送中</span>
                        <span v-if="dialogitem.orderstate === '4'" style="color: green;">已完成</span>
                        <span v-if="dialogitem.orderstate === '5'" style="color: #CECECE;">已取消</span>
                    </span>
                </div>
                <div class="containbox" style="background-color: #CECECE; border-radius: 20px; position: relative;margin-top: 10px;">
                    <div style="padding-top: 10px; display: flex;">
                        <div style="margin-left: 2%; width: 500px;">用户名：{{ dialogitem.username }}</div>
                        <div style="margin-left: 4%;">手机号：{{ dialogitem.phonenumber }}</div>
                    </div>
                    <div style="margin-top: 10px; margin-left: 2%; max-width: 750px; white-space: normal;">备注：{{ dialogitem.note }}</div>
                    <div style="margin-top: 10px; margin-left: 2%; padding-bottom: 10px;">
                        <span>下单时间：{{ dialogitem.ordertime }}</span>
                        <span style="margin-left: 14.5%;">预计送达时间：{{ dialogitem.perdicttime }}</span>
                    </div>
                </div>
                <div class="containbox" style="margin-top: 5px; background-color: #CECECE; border-radius: 20px; position: relative; padding-top: 10px; padding-left: 2%; padding-bottom: 10px; padding-right: 2%;">
                    <div>
                        <span>菜品名称</span>
                        <span style="margin-left: 68%;">数量</span>
                        <span style="margin-left: 6%;">单价</span>
                    </div>
                    <hr style="height: 1px; border-top: 3px solid black;">
                    <div v-for="(item) in dialogitem.info" style="margin-top: 10px; display: flex;">
                        <div style="width: 500px; white-space: normal;">{{ item.name }}</div>
                        <div style="margin-left: 13%;">x{{ item.num }}</div>
                        <div style="margin-left: 6%;">￥ {{ item.price }}</div>
                    </div>
                    <hr style="height: 1px; border-top: 3px solid black;">
                    <div>
                        <span>菜品小计：</span>
                        <span style="margin-left: 74%">￥ {{ dialogitem.totalmchpricenote }}</span>
                    </div>
                </div>
                <div class="containbox" style="margin-top: 5px; background-color: #CECECE; border-radius: 20px; position: relative; padding-top: 10px; padding-left: 2%; padding-bottom: 10px; padding-right: 2%;">
                    <div style="display: flex;">
                        <div style="width: 300px;">派送费：￥ {{ dialogitem.deliveryCharge }}</div>
                        <div style="margin-left: 41%;">打包费：￥ {{ dialogitem.packingCharge }}</div>
                    </div>
                    <div style="margin-top: 10px;">菜品小计：￥ {{ dialogitem.totalmchpricenote }}</div>
                    <div style="margin-top: 10px;">送达时间：{{ dialogitem.ordertime }}</div>
                    <div style="margin-top: 10px;">支付方式：{{ dialogitem.realtime }}</div>
                    <div style="margin-top: 10px; margin-left: 65%;">合计：
                        <span style=" font-size: 50px;">
                            ￥{{ dialogitem.money }}
                        </span>
                    </div>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">关 闭</el-button>
                <el-button type="danger" @click="handledialogdelete">删 除</el-button>
                <el-button type="danger" @click="handledialogreject" v-if="tableData[dialogindex].orderstate === '1'">拒 单</el-button>
                
                <el-button type="danger" @click="handledialogcancle" v-if="tableData[dialogindex].orderstate === '2'">取 消</el-button>
                <el-button type="primary" @click="handledialogaccept" v-if="tableData[dialogindex].orderstate === '1'">接 单</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import { color } from 'echarts';

    export default {
        data() {
            return {
                // tabs=======================================================================================================
                activeName: 'first',
                dialogVisible: false,
                dialogindex:0,
                dialogitem:{
                        orderID:'1332666559995544599',
                        username:'爱吃海底捞',
                        phonenumber:'13655263459',
                        location:'北京邮电大学学十三公寓美团外卖柜',
                        ordertime:'2024-08-30 10:30:45',
                        perdicttime:'2024-08-30 11:00:45',
                        realtime:'2024-08-30 10:55:45',
                        note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
                        info:[ 
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '2'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            }
                        ],
                        packingCharge:'5',
                        deliveryCharge:'5',
                        totalmchpricenote:'9999',
                        // payway: 1.微信支付  2.支付宝支付
                        payway:'1',
                        money:'154',
                        orderstate:'1'
                    },
                //timeselecter=======================================================================================================
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
                //searcher=======================================================================================================
                input:'',
                //table========================================================================================================
                tableData: [
                    // orderstate: 1.待接单  2.待派送  3.派送中  4.已完成  5.已取消
                    {
                        orderID:'1332666559995544599',
                        username:'不爱吃海底捞',
                        phonenumber:'13655263459',
                        location:'北京邮电大学学十三公寓美团外卖柜',
                        ordertime:'2024-08-30 10:30:45',
                        perdicttime:'2024-08-30 11:00:45',
                        realtime:'2024-08-30 10:55:45',
                        note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
                        info:[ 
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '2'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            }
                        ],
                        packingCharge:'5',
                        deliveryCharge:'5',
                        totalmchpricenote:'9999',
                        // payway: 1.微信支付  2.支付宝支付
                        payway:'1',
                        money:'154',
                        orderstate:'1'
                    }, {
                        orderID:'1332666559995544598',
                        username:'爱吃海底捞',
                        phonenumber:'13655263459',
                        location:'北京邮电大学学十三公寓美团外卖柜',
                        ordertime:'2024-08-30 10:30:45',
                        perdicttime:'2024-08-30 11:00:45',
                        realtime:'2024-08-30 10:55:45',
                        note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
                        info:[ 
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '2'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            }
                        ],
                        packingCharge:'5',
                        deliveryCharge:'5',
                        totalmchpricenote:'9999',
                        // payway: 1.微信支付  2.支付宝支付
                        payway:'1',
                        money:'154',
                        orderstate:'2'
                    },  {
                        orderID:'1332666559995544597',
                        username:'爱吃海底捞',
                        phonenumber:'13655263459',
                        location:'北京邮电大学学十三公寓美团外卖柜',
                        ordertime:'2024-08-30 10:30:45',
                        perdicttime:'2024-08-30 11:00:45',
                        realtime:'2024-08-30 10:55:45',
                        note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
                        info:[ 
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '2'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            }
                        ],
                        packingCharge:'5',
                        deliveryCharge:'5',
                        totalmchpricenote:'9999',
                        // payway: 1.微信支付  2.支付宝支付
                        payway:'1',
                        money:'154',
                        orderstate:'3'
                    },  {
                        orderID:'1332666559995544596',
                        username:'不爱吃海底捞',
                        phonenumber:'13655263459',
                        location:'北京邮电大学学十三公寓美团外卖柜',
                        ordertime:'2024-08-30 10:30:45',
                        perdicttime:'2024-08-30 11:00:45',
                        realtime:'2024-08-30 10:55:45',
                        note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
                        info:[ 
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '2'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            }
                        ],
                        packingCharge:'5',
                        deliveryCharge:'5',
                        totalmchpricenote:'9999',
                        // payway: 1.微信支付  2.支付宝支付
                        payway:'1',
                        money:'154',
                        orderstate:'4'
                    },  {
                        orderID:'1332666559995544595',
                        username:'爱吃海底捞',
                        phonenumber:'13655263459',
                        location:'北京邮电大学学十三公寓美团外卖柜',
                        ordertime:'2024-08-30 10:30:45',
                        perdicttime:'2024-08-30 11:00:45',
                        realtime:'2024-08-30 10:55:45',
                        note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
                        info:[ 
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '2'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            }
                        ],
                        packingCharge:'5',
                        deliveryCharge:'5',
                        totalmchpricenote:'9999',
                        // payway: 1.微信支付  2.支付宝支付
                        payway:'1',
                        money:'154',
                        orderstate:'5'
                    },  {
                        orderID:'1332666559995544594',
                        username:'爱吃海底捞',
                        phonenumber:'13655263459',
                        location:'北京邮电大学学十三公寓美团外卖柜',
                        ordertime:'2024-08-30 10:30:45',
                        perdicttime:'2024-08-30 11:00:45',
                        realtime:'2024-08-30 10:55:45',
                        note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
                        info:[ 
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '2'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            },
                            {
                                name: '六神清爽小火锅',
                                price: '198',
                                num: '1'
                            }
                        ],
                        packingCharge:'5',
                        deliveryCharge:'5',
                        totalmchpricenote:'9999',
                        // payway: 1.微信支付  2.支付宝支付
                        payway:'1',
                        money:'154',
                        orderstate:'1'
                    }, 
                ]
            };
        },
        methods: {
            rowStyle({ rowIndex }) {
                // 如果行索引是偶数，则返回灰色背景
                if (rowIndex % 2 === 0) {
                    return { backgroundColor: '#CECECE', color: 'black' }; // 浅灰色
                }
                return { color: 'black' }; // 默认样式
            },
            // 搜索功能
            handleSearch() {
                this.filteredData;
                
            },
            // 时间选择功能
            handleDateChange() {
                this.filteredData;
            },
            // 标签页切换功能
            handleTabClick(tab) {
                this.filteredData;
            },
            //searcher=======================================================================================================
            handleClick(tab, event) {
                console.log(tab, event);
            },
            
            
            handleSelect(item) {
                console.log(item);
            },
            acceptOrder(index) {
                // 如果当前标签页是“全部订单”
                if (this.activeName === 'first') {
                this.tableData[index].orderstate = '2';
                } 
                // 如果当前标签页是“待接单”
                else if (this.activeName === 'second') {  // 假设“待接单”标签页的 name 是 'second'
                // 计算在“待接单”标签页中索引对应的订单
                let count = 0;
                for (let i = 0; i < this.tableData.length; i++) {
                    if (this.tableData[i].orderstate === '1') {
                    if (count === index) {
                        this.tableData[i].orderstate = '2';
                        break;
                    }
                    count++;
                    }
                }
                }
            },
            rejectOrder(index) {
                // 如果当前标签页是“全部订单”
                if (this.activeName === 'first') {
                this.tableData[index].orderstate = '5';
                } 
                // 如果当前标签页是“待接单”
                else if (this.activeName === 'second') {  // 假设“待接单”标签页的 name 是 'second'
                // 计算在“待接单”标签页中索引对应的订单
                let count = 0;
                for (let i = 0; i < this.tableData.length; i++) {
                    if (this.tableData[i].orderstate === '1') {
                    if (count === index) {
                        this.tableData[i].orderstate = '5';
                        break;
                    }
                    count++;
                    }
                }
                }
            },
            cancelOrder(index) {
                // 如果当前标签页是“全部订单”
                if (this.activeName === 'first') {
                this.tableData[index].orderstate = '5';
                } 
                // 如果当前标签页是“待接单”
                else if (this.activeName === 'third') {  // 假设“待接单”标签页的 name 是 'second'
                // 计算在“待接单”标签页中索引对应的订单
                let count = 0;
                for (let i = 0; i < this.tableData.length; i++) {
                    if (this.tableData[i].orderstate === '2') {
                    if (count === index) {
                        this.tableData[i].orderstate = '5';
                        break;
                    }
                    count++;
                    }
                }
                }
            },
            deleteOrder(index) {
                // 如果当前标签页是“全部订单”
                if (this.activeName === 'first') {
                    this.tableData.splice(index, 1);
                } 
                // 如果当前标签页是“待接单”
                else if (this.activeName === 'second') {  // 假设“待接单”标签页的 name 是 'second'
                // 计算在“待接单”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '1') {
                        if (count === index) {
                            this.tableData.splice(i, 1);
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“待派送”
                else if (this.activeName === 'third') {  // 假设“待派送”标签页的 name 是 'third'
                // 计算在“待派送”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '2') {
                        if (count === index) {
                            this.tableData.splice(i, 1);
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“派送中”
                else if (this.activeName === 'fourth') {  // 假设“派送中”标签页的 name 是 'fourth'
                // 计算在“派送中”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '3') {
                        if (count === index) {
                            this.tableData.splice(i, 1);
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“已完成”
                else if (this.activeName === 'fifth') {  // 假设“已完成”标签页的 name 是 'fifth'
                // 计算在“已完成”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '4') {
                        if (count === index) {
                            this.tableData.splice(i, 1);
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“已取消”
                else if (this.activeName === 'sixth') {  // 假设“已取消”标签页的 name 是 'sixth'
                // 计算在“已取消”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '5') {
                        if (count === index) {
                            this.tableData.splice(i, 1);
                            break;
                        }
                        count++;
                        }
                    }
                }
            },
            handleopen(newdialogindex)
            {
                // 如果当前标签页是“待接单”
                if (this.activeName === 'second') {  // 假设“待接单”标签页的 name 是 'second'
                // 计算在“待接单”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '1') {
                        if (count === newdialogindex) {
                            newdialogindex = i;
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“待派送”
                else if (this.activeName === 'third') {  // 假设“待派送”标签页的 name 是 'third'
                // 计算在“待派送”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '2') {
                        if (count === newdialogindex) {
                            newdialogindex = i;
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“派送中”
                else if (this.activeName === 'fourth') {  // 假设“派送中”标签页的 name 是 'fourth'
                // 计算在“派送中”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '3') {
                        if (count === newdialogindex) {
                            newdialogindex = i;
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“已完成”
                else if (this.activeName === 'fifth') {  // 假设“已完成”标签页的 name 是 'fifth'
                // 计算在“已完成”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '4') {
                        if (count === newdialogindex) {
                            newdialogindex = i;
                            break;
                        }
                        count++;
                        }
                    }
                }
                // 如果当前标签页是“已取消”
                else if (this.activeName === 'sixth') {  // 假设“已取消”标签页的 name 是 'sixth'
                // 计算在“已取消”标签页中索引对应的订单
                    let count = 0;
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].orderstate === '5') {
                        if (count === newdialogindex) {
                            newdialogindex = i;
                            break;
                        }
                        count++;
                        }
                    }
                }
                this.dialogitem = this.tableData[newdialogindex];
                
                this.dialogindex = newdialogindex;
                
                this.dialogVisible = true;
                //console.log(this.dialogVisible);
                //console.log(this.dialogindex);
            },
            handledialogreject()
            {
                this.tableData[this.dialogindex].orderstate = '5';
                this.dialogVisible = false;
            },
            handledialogdelete()
            {
                this.tableData.splice(this.dialogindex, 1);
                this.dialogVisible = false;
            },
            handledialogaccept()
            {
                this.tableData[this.dialogindex].orderstate = '2';
                this.dialogVisible = false;
            },
            handledialogcancle()
            {
                this.tableData[this.dialogindex].orderstate = '5';
                this.dialogVisible = false;
            }
        },
        mounted() {
            //searcher=======================================================================================================
            this.restaurants = this.loadAll();
        },
        computed: {
            filteredData() {
                let filtered = this.tableData;

                // 按用户名搜索
                if (this.input) {
                    filtered = filtered.filter(item =>
                        item.username.includes(this.input)
                    );
                }

                // 按时间范围筛选
                if (this.date.length === 2) {
                    const [startDate, endDate] = this.date;
                    filtered = filtered.filter(item => {
                        const orderDate = new Date(item.ordertime);
                        return orderDate >= startDate && orderDate <= endDate;
                    });
                }

                // 按标签页状态筛选
                if (this.activeName !== 'first') {
                    const stateMap = {
                        second: '1',
                        third: '2',
                        fourth: '3',
                        fifth: '4',
                        sixth: '5'
                    };
                    filtered = filtered.filter(item =>
                        item.orderstate === stateMap[this.activeName]
                    );
                }

                return filtered;
            }
        }
    };
</script>
<style scoped>
    .container {
        position: relative;
    }
    /* timeselecter========================================================================================================= */
    .timeselecter {
        position: absolute;
        top: 20px; /* Adjust as needed */
        right: 230px; /* Adjust as needed */
        z-index: 2; /* Ensure time picker is on top */
    }
    /* searcher========================================================================================================= */
    .searcher {
        position: absolute;
        top: 20px; /* Adjust as needed */
        right: 20px; /* Adjust as needed */
        z-index: 2; /* Ensure time picker is on top */
    }



    /* tab========================================================================================================= */
    .tab {
        position: absolute;
        z-index: 1; /* Ensure tabs are underneath the time picker */
        top: 20px;
        left: 50px;
    }
    ::v-deep .el-tabs__item.is-active {
        color: black !important; /* 修改选中标签的文字颜色为黑色 */
        font-weight: bold !important; /* 使选中标签的文字加粗 */
    }
    ::v-deep .el-tabs__item {
        color: black; /* 修改标签文字颜色 */
        font-size: 17px;
        width: 70px; /* 设置每个tab头部的固定宽度 */
        text-align: center; /* 可选：让标签内容居中 */
    }
    ::v-deep .el-tabs__active-bar {
        background-color: black !important; /* 修改选中标签底部横线的颜色 */
    }

    ::v-deep .el-table .gray-row {
        background: #CECECE;
    }
</style>