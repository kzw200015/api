<template>
  <el-menu
      :default-active="route.path"
      mode="horizontal"
      router
  >
  </el-menu>
</template>

<script setup lang="ts">
import { useRoute } from "vue-router"
import { reactive } from "vue"

const route = useRoute()
const navItems = reactive({})
</script>
<style scoped>

</style>