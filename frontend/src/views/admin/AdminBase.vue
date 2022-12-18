<template>
  <div class="admin-container">
    <head-nav-bar></head-nav-bar>
    <div class="admin-main-container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import HeadNavBar from "./HeadNavBar.vue"</script>

<style scoped>
.admin-container {
  height: 100%;
  display: grid;
  grid-template-rows: auto 1fr;
}

.admin-main-container {
  overflow-y: auto;
}
</style>