<template>
    <div class="container">
        <div class="form-box">
            <!-- 商家登录和注册 -->
            <div class="merchant-container hidden">
                <div class="merchant-box">
                    <h2 class="login-title"><span>没有账号，去</span>注册</h2>
                    
                    <div class="input-box">
                        <input type="text" placeholder="用户名">
                        <input type="password" placeholder="密码">
                    </div>
                    <button>登录</button>
                </div>
                <div class="merchant-register-box slide-up">
                    <h2 class="register-title"><span>已有账号，去</span>登录</h2>
                    
                    <div class="input-box">
                        <input type="text" placeholder="用户名">
                        <input type="password" placeholder="密码">
                        <input type="password" placeholder="确认密码">
                    </div>
                    <button>注册</button>
                </div>
            </div>
            <!-- 顾客登录和注册 -->
            <div class="customer-container">
                <div class="customer-box">
                    <h2 class="login-title"><span>没有账号，去</span>注册</h2>
                    <div class="input-box">
                        <input type="text" placeholder="用户名">
                        <input type="password" placeholder="密码">
                    </div>
                    <button>登录</button>
                </div>
                <div class="customer-register-box slide-up">
                    <h2 class="register-title"><span>已有账号，去</span>登录</h2>
                    <div class="input-box">
                        <input type="text" placeholder="用户名">
                        <input type="password" placeholder="密码">
                        <input type="password" placeholder="确认密码">
                    </div>
                    <button>注册</button>
                </div>
            </div>
        </div>
        <div class="con-box right">
            <h2>欢迎来到<span>邮外卖</span></h2>
            <p>快来登录<span>点外卖</span>吧</p>
            <img src="../assets/images/login/1.png" alt="">
            <p>我是商家</p>
            <button id="merchant" @click="showMerchant">去入驻</button>
        </div>
        <div class="con-box left">
            <h2>欢迎来到<span>邮外卖</span></h2>
            <p>快来入驻<span>商家</span>吧</p>
            <img src="../assets/images/login/2.png" alt="">
            <p>我是顾客</p>
            <button id="customer" @click="showCustomer">去购物</button>
        </div>
    </div>
</template>

<script scoped>
export default {
    mounted() {
        this.initEventListeners();
    },
    methods: {
        initEventListeners() {
            let login_titles = document.querySelectorAll('.login-title');
            let register_titles = document.querySelectorAll('.register-title');

            login_titles.forEach(title => {
                title.addEventListener('click', (e) => {
                    let container = e.target.closest('.merchant-container, .customer-container');
                    let login_box = container.querySelector('.merchant-box, .customer-box');
                    let register_box = container.querySelector('.merchant-register-box, .customer-register-box');
                    
                    if (register_box.classList.contains('slide-up')) {
                        register_box.classList.remove('slide-up');
                        login_box.classList.add('slide-up');
                    }
                });
            });

            register_titles.forEach(title => {
                title.addEventListener('click', (e) => {
                    let container = e.target.closest('.merchant-container, .customer-container');
                    let login_box = container.querySelector('.merchant-box, .customer-box');
                    let register_box = container.querySelector('.merchant-register-box, .customer-register-box');
                    
                    if (login_box.classList.contains('slide-up')) {
                        login_box.classList.remove('slide-up');
                        register_box.classList.add('slide-up');
                    }
                });
            });
        },
        showMerchant() {
            let form_box = document.getElementsByClassName('form-box')[0];
            let merchant_container = document.getElementsByClassName('merchant-container')[0];
            let customer_container = document.getElementsByClassName('customer-container')[0];

            form_box.style.transform = 'translateX(80%)';
            customer_container.classList.add('hidden');
            merchant_container.classList.remove('hidden');
        },
        showCustomer() {
            let form_box = document.getElementsByClassName('form-box')[0];
            let merchant_container = document.getElementsByClassName('merchant-container')[0];
            let customer_container = document.getElementsByClassName('customer-container')[0];

            form_box.style.transform = 'translateX(0%)';
            merchant_container.classList.add('hidden');
            customer_container.classList.remove('hidden');
        }
    }
}
</script>

<style>
/* 初始化样式 */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(200deg, #f3e7e9, #e3eeff);
    font-family: 'Arial', sans-serif;
}

.container {
    background-color: #fff;
    width: 650px;
    height: 415px;
    border-radius: 5px;
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
    position: relative;
    /* overflow: hidden; 确保隐藏的部分不会溢出容器 */
}

.form-box {
    position: absolute;
    top: -10%;
    left: 5%;
    width: 320px;
    height: 500px;
    background-color: #d3b7d8;
    border-radius: 5px;
    box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
    z-index: 2;
    transition: 0.5s ease-in-out;
    overflow: hidden; /* 确保上下滑动部分不会溢出容器 */
}

.merchant-container, .customer-container {
    position: relative;
    width: 100%;
    height: 100%;
    overflow: hidden; /* 确保滑动时未显示的部分不会溢出 */
}

.merchant-box, .customer-box, .merchant-register-box, .customer-register-box {
    position: absolute;
    width: 100%;
    height: 100%; /* 设置高度为100%确保滑动时的正确显示 */
    top: 0;
    left: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    transition: 0.6s ease;
}

.merchant-register-box.slide-up, .customer-register-box.slide-up {
    top: 100%; /* 向下滑动将其移出视野 */
    opacity: 0; /* 隐藏不显示的部分 */
    visibility: hidden;
}

.merchant-box.slide-up, .customer-box.slide-up {
    top: -100%; /* 向上滑动将其移出视野 */
    opacity: 0; /* 隐藏不显示的部分 */
    visibility: hidden;
}

.input-box {
    width: 70%;
    margin-top: 15px;
}

.input-box input {
    width: 100%;
    padding: 10px;
    background-color: transparent;
    border: none;
    border-bottom: 1px solid rgba(255, 255, 255, 0.4);
    color: #fff;
    margin: 8px 0;
    font-size: 14px;
    letter-spacing: 2px;
}

.input-box input::placeholder {
    color: #fff;
}

.input-box input:focus {
    outline: none;
    border-bottom: 1px solid #a262ad80;
    color: #a262ad;
    transition: border-bottom 0.5s ease;
}

.input-box input:focus::placeholder {
    opacity: 0;
}

.merchant-box button, .customer-box button, .merchant-register-box button, .customer-register-box button {
    width: 70%;
    margin-top: 35px;
    background-color: #f6f6f6;
    border: none;
    border-radius: 8px;
    padding: 13px;
    color: #a262ad;
    letter-spacing: 2px;
    cursor: pointer;
    transition: background-color 0.5s ease;
}

.merchant-box button:hover, .customer-box button:hover, .merchant-register-box button:hover, .customer-register-box button:hover {
    background-color: #a262ad;
    color: #f6f6f6;
}

.hidden {
    display: none;
    transition: 0.5s;
}

h2 {
    text-align: center;
    margin-bottom: 25px;
    color: #fff;
    text-transform: uppercase;
    letter-spacing: 5px;
    cursor: pointer;
}

.con-box {
    width: 50%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
}

.con-box.left {
    left: -2%;
}

.con-box.right {
    right: -2%;
}

.con-box h2 {
    color: #8e9aaf;
    font-size: 25px;
    font-weight: bold;
    letter-spacing: 3px;
    text-align: center;
    margin-bottom: 4px;
}

.con-box p {
    font-size: 12px;
    letter-spacing: 2px;
    color: #8e9aaf;
    text-align: center;
}

.con-box span {
    color: #d3b7d8;
}

.con-box img {
    width: 150px;
    height: 150px;
    opacity: 0.9;
    margin: 40px 0;
}

.con-box button {
    margin-top: 3%;
    background-color: #fff;
    color: #a262ad;
    border: 1px solid #d3b7d8;
    padding: 6px 10px;
    border-radius: 5px;
    letter-spacing: 1px;
    outline: none;
    cursor: pointer;
    transition: background-color 0.5s ease;
}

.con-box button:hover {
    background-color: #d3b7d8;
    color: #fff;
}
</style>