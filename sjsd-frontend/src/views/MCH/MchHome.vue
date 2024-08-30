<!-- 商家首页 -->
 <!-- "#/mch" -->

<template>
    <div class="home">
      <el-container>
        <el-header style="background-color: aqua">
          <el-menu
            :default-active="activeIndex"
            class="MCH-menu-h"
            mode="horizontal"
            @select="handleSelect"
            style="background-color: aqua"
          >
            <el-menu-item index="1">
                <router-link to="/">LOGO</router-link>
            </el-menu-item>
            <el-menu-item index="2" id="photo">
              <el-avatar :size="40" :src="circleUrl"></el-avatar>
            </el-menu-item>
            <el-menu-item index="3" id="name">用户名</el-menu-item>
          </el-menu>
        </el-header>
  
        <el-container class="container">
          <div class="aside">
            <el-aside width="150px" height="100%">
              <el-col :span="24">
                <el-menu
                  default-active="1"
                  class="MCH-menu-v"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b"
                >
                  <el-menu-item index="1">
                    <i class="el-icon-house"></i>
                    <span slot="title">
                        <router-link to="/mch"> 首页 </router-link>
                    </span>
                  </el-menu-item>
                  <el-menu-item index="2">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">
                        <router-link to="/mch/order">订单管理</router-link>
                    </span>
                  </el-menu-item>
                  <el-menu-item index="3">
                    <i class="el-icon-menu"></i>
                    <span slot="title">
                        <router-link to="/mch/menu">菜品管理</router-link>
                    </span>
                  </el-menu-item>
                  <el-menu-item index="4">
                    <i class="el-icon-setting"></i>
                    <span slot="title">
                        <router-link to="/mch/detail">商家详情</router-link>
                    </span>
                  </el-menu-item>
                  <el-menu-item index="5">
                    <i class="el-icon-odometer"></i>
                    <span slot="title">
                        <router-link to="/mch/data">数据统计</router-link>
                    </span>
                  </el-menu-item>
                </el-menu>
              </el-col>
            </el-aside>
          </div>
  
          <el-main>
            <!-- 核心区域代码 -->
          </el-main>
        </el-container>
      </el-container>
    </div>
  </template>
  
  
  <script>
  export default {
    data() {
      return {
        circleUrl:
          "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      };
    },
    methods: {
      handleSelect: function () {
        // alert("Select");
      },
    },
  };
  </script>
  
  <style>
  .MCH-menu-v {
    height: 90vh;
  }
  
  #photo {
    left: 75%;
  }
  
  #name {
    left: 75%;
  }
  </style>