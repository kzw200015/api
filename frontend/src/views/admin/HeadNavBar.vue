<template>
  <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      :ellipsis="false"
  >
    <el-menu-item @click="router.push('/')" index="/">{{ "Kong's Blog" }}</el-menu-item>
    <div style="flex-grow: 1"/>
    <el-menu-item @click="router.push(item.path)" v-for="item in navItems" :index="item.path" :key="item.path">
      {{ item.name }}
    </el-menu-item>
    <div style="flex-grow: 1"/>
    <el-sub-menu index="user">
      <template #title>{{ currentUser?.username }}</template>
      <el-menu-item @click="handleLogout">登出</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router"
import { computed, onMounted, reactive, ref } from "vue"
import { getMe, logout, User } from "../../api"

const route = useRoute()
const router = useRouter()
const navItems = reactive([
  {
    name: "文章管理",
    path: "/admin/posts"
  },
  {
    name: "系统管理",
    path: "/admin/system"
  }
])

const currentUser = ref<User | undefined>(undefined)

onMounted(async () => {
  currentUser.value = await getMe()
})


const handleLogout = async () => {
  await logout()
  await getMe()
}

const activeIndex = computed(() => {
  return navItems.find(it => route.path.startsWith(it.path))?.path
})
</script>
<style scoped>

</style>