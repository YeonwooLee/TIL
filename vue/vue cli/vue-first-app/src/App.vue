<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <header-nav></header-nav>
    <MainContent></MainContent>
    <FooterBar></FooterBar>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import HeaderNav from './components/HeaderNav.vue'
import MainContent from './components/MainContent.vue'
import FooterBar from './components/FooterBar.vue'

export default {
  name: 'App',
  components: {
    // HelloWorld,
    HeaderNav,
    MainContent,
    FooterBar,
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
