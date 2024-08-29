<template>
  <div>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <router-view/>
    <!-- 当用户点击导航链接（例如 <router-link to="/">Home</router-link>），URL 会改变，Vue Router 会根据当前 URL 匹配到相应的路由配置。
匹配到的组件（例如 HomeView 或 AboutView）会被渲染到 <router-view/> 位置。 -->
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
