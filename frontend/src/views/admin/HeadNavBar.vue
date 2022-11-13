<template>
  <el-menu
      :default-active="route.path"
      mode="horizontal"
      :ellipsis="false"
  >
    <el-menu-item @click="router.push(item.path)" v-for="item in navItems" :index="item.path" :key="item.path">
      {{ item.name }}
    </el-menu-item>
    <div style="flex-grow: 1"/>
    <el-menu-item @click="router.push('/')" index="/">{{ "Kong's Blog" }}</el-menu-item>
    <el-sub-menu index="user">
      <template #title>{{ currentUser?.username }}</template>
      <el-menu-item @click="handleLogout">登出</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router"
import { onMounted, reactive, ref } from "vue"
import { getMe, logout, User } from "../../api"
import store from "../../store"

const route = useRoute()
const router = useRouter()
const navItems = reactive([
  {
    name: "文章管理",
    path: "/admin/posts"
  }
])

const currentUser = ref<User | undefined>(undefined)

onMounted(async () => {
  currentUser.value = await store.userStore.getMe()
})


const handleLogout = async () => {
  await logout()
  await getMe()
}
</script>
<style scoped>

</style>