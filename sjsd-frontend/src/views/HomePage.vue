<template>
    <div class="container">
      <div class="back" :class="{ 'show': isBackShown }"></div>
      
      <!-- Left Circle -->
      <div class="circle lcircle" :class="{ 'open': isLeftOpen }" @mouseover="handleMouseOver('left')" @mouseout="handleMouseOut('left')">
        <div class="ltext">
          <div class="stitle">商家入驻</div>
          <div class="sintro">加入舌尖速递，开启您的在线商业之旅。我们提供全面的技术支持和营销策略，帮助您的餐厅吸引更多顾客，提高品牌知名度。</div>
          <div class="sintro">通过我们的平台，您可以轻松管理订单，优化菜单，并获取深入的数据分析，以提升您的业务效率。</div>
        </div>
      </div>
  
      <!-- Right Circle -->
      <div class="circle rcircle" :class="{ 'open': isRightOpen }" @mouseover="handleMouseOver('right')" @mouseout="handleMouseOut('right')">
        <div class="rtext">
          <div class="stitle">舌尖速递</div>
          <div class="sintro">我们致力于提供最优质的外卖服务，让每一位顾客都能享受到便捷的订餐体验和美味的佳肴。</div>
          <div class="sintro">无论是家庭聚餐还是办公室午餐，舌尖速递都能满足您的需求。我们承诺快速配送，确保每一份食物都新鲜可口。</div>
        </div>
      </div>
  
      <!-- Center -->
      <div class="center" @mouseover="handleMouseOver('center')">
        <div id="title">舌尖速递</div>
        <div id="line1">您的美食，我们的使命</div>
        <div id="line2">在线订餐，轻松快捷</div>
        <div id="line3">发现周边美食，享受生活每一刻</div>
        <div id="line4">立即体验舌尖速递，让美味触手可及</div>
        <button class="center-button" @click="onCentralButtonClick">立即体验</button>
      </div>
    </div>
  </template>
  


<script>
export default {
  data() {
    return {
      isLeftOpen: false,
      isRightOpen: false,
      isBackShown: false
    };
  },
  methods: {
    handleMouseOver(side) {
      if (side === 'left' && !this.isLeftOpen) {
        this.isLeftOpen = true;
        this.isBackShown = true;
      } else if (side === 'right' && !this.isRightOpen) {
        this.isRightOpen = true;
        this.isBackShown = true;
      }
    },
    handleMouseOut(side) {
      if (side === 'left') {
        this.isLeftOpen = false;
        this.isBackShown = this.isRightOpen;
      } else if (side === 'right') {
        this.isRightOpen = false;
        this.isBackShown = this.isLeftOpen;
      }
    },
    onCentralButtonClick() {
        this.$router.push(
            {
                path:"/login"
            }
        )
    }
  }
};
</script>
  
  <style scoped>
  .container {
    width: 100%;
    height: 100vh; /* 使用视口高度 */
    overflow: hidden;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .circle {
    width: 500px;
    height: 700px;
    z-index: 5;
    position: absolute;
    opacity: 0.2;
  }
  .lcircle:hover,
  .rcircle:hover {
    z-index: 10;
    opacity: 0.8;
  }
  .lcircle .ltext,
  .rcircle .rtext {
    background-color: rgba(0, 0, 0, 0.6);
    color: #ffffff;
    font-family: 'Roboto', sans-serif;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.7);
    padding: 20px;
    border-radius: 10px;
  }
  .lcircle.open .ltext .stitle,
  .rcircle.open .rtext .stitle {
    font-size: 36px;
    line-height: 1.2;
    margin-bottom: 10px;
  }
  .sintro {
    font-size: 18px;
    line-height: 1.5;
  }
  .lcircle {
    left: 0;
    top: 45%;
    transform: translate(0, -50%);
    z-index: 1;
  }
  .lcircle::after {
    content: "";
    background: url('~@/assets/images/homepage/food1.jpg') no-repeat;
    background-size: cover;
    width: 1000px;
    height: 1000px;
    border-radius: 50%;
    position: absolute;
    top: -20%;
    left: -100%;
    z-index: -1;
  }
  .lcircle.open {
    opacity: 0.95;
    z-index: 20;
  }
  .lcircle.open::after {
    width: 1200px;
    height: 1200px;
  }
  .rcircle {
    right: 0;
    top: 45%;
    transform: translate(0, -50%);
    z-index: 1;
  }
  .rcircle::after {
    content: "";
    background: url('~@/assets/images/homepage/food2.jpg') no-repeat;
    background-size: cover;
    width: 1000px;
    height: 1000px;
    border-radius: 50%;
    position: absolute;
    top: -20%;
    right: -100%;
    z-index: -1;
  }
  .rcircle:hover {
    z-index: 10;
    opacity: 0.8;
  }
  .rcircle.open {
    opacity: 0.8;
    z-index: 20;
  }
  .rcircle.open::after {
    width: 1200px;
    height: 1200px;
  }
  .lcircle .ltext {
    background-color: lightgray;
    width: 300px;
    height: 300px;
    position: absolute;
    top: 20%;
    left: 10%;
    z-index: 30;
    text-align: left;
  }
  .lcircle.open .ltext {
    background-color: lightgray;
    left: 20%;
  }
  .lcircle.open .ltext .stitle {
    font-size: 40px;
    line-height: 60px;
  }
  .rcircle .rtext {
    background-color: lightgray;
    width: 300px;
    height: 300px;
    position: absolute;
    top: 20%;
    right: 10%;
    z-index: 100;
    text-align: right;
  }
  .rcircle.open .rtext {
    background-color: lightgray;
    right: 20%;
  }
  .rcircle.open .rtext .stitle {
    font-size: 40px;
    line-height: 60px;
  }
  

  .back {
    width: 100%;
    height: 100%;
    position: absolute;
    background-color: rgba(0, 0, 0, 0.463);
    opacity: 0;
    z-index: -1;
  }
  .back.show {
    opacity: 0.7;
    z-index: 15;
  }

  .container {
  width: 100%;
  height: 100vh; /* 使用视口高度 */
  overflow: hidden;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}

.center {
  background-color: rgba(168, 164, 164, 0.479);
  width: 900px;
  height: 500px; /* 增加高度 */
  border-radius: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  z-index: 5;
}

.center-button {
  margin-top: 40px;
  padding: 15px 30px;
  font-size: 18px;
  background-color: #ff5722;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.center-button:hover {
  background-color: #e64a19;
}

#title {
  font-size: 90px;
  font-weight: 500;
}

#line1 {
  font-size: 48px;
  line-height: 90px;
}

#line2 {
  font-size: 21px;
  line-height: 60px;
}

#line3 {
  font-size: 14px;
  line-height: 32px;
}
  </style>