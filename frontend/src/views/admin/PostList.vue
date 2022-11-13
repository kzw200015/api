<template>
  <el-table :data="posts" height="100%">
    <el-table-column prop="title" label="标题" width="180"/>
    <el-table-column prop="createTime" label="创建时间" width="180"/>
    <el-table-column prop="updateTime" label="更新时间"/>
    <el-table-column prop="userId" label="用户"/>
  </el-table>
</template>

<script setup lang="ts">
import { getPosts, Post } from "../../api"
import { onMounted, ref } from "vue"

const page = ref(0)
const size = ref(10)
const total = ref(0)

const posts = ref<Post[]>([])

const loadPosts = async () => {
  const postPage = await getPosts(page.value, size.value)
  total.value = postPage.total
  posts.value = postPage.values
}

onMounted(async () => {
  await loadPosts()
})
</script>
<style scoped>

</style>