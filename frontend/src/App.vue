<template>
  <keep-alive>
    <component v-bind:is="isAuthenticated()"/>

  </keep-alive>
</template>

<script>
  import Login from './components/Login';
  import Shablon from './components/Shablon';

  export default {
    name: 'App',
    components: {
      shablon: Shablon,
      login: Login
    },
    methods: {
      isAuthenticated() {
        this.$store.commit("isAuthenticated");
        return this.$store.state.authenticated.isAuthenticated == false ? "login" : "shablon";
//        return "shablon"
      }
    },
    data: () => ({})

  }
</script>

<style>
  body {
    background-color: #0074D9;
  }
</style>
