<template>
  <el-container>
    <el-header>
      <head-nav-bar></head-nav-bar>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import HeadNavBar from "./HeadNavBar.vue"</script>

<style scoped>

</style>