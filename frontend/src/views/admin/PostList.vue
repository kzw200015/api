<template>
  <div class="container">
    <el-table :data="posts" height="100%">
      <el-table-column prop="title" label="标题"/>
      <el-table-column label="创建时间">
        <template #default="scope">
          {{ formatDateTime(scope.row.createTime) }}
        </template>
      </el-table-column>
      <el-table-column label="更新时间">
        <template #default="scope">
          {{ formatDateTime(scope.row.updateTime) }}
        </template>
      </el-table-column>
      <el-table-column label="用户">
        <template #default="scope">
          {{ users.find(it => it.id === scope.row.userId)?.username }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default>
          <el-button>编辑</el-button>
          <el-button type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination v-model:current-page="page" layout="prev,pager,next" :total="total"/>
  </div>
</template>

<script setup lang="ts">
import { getPosts, getUsers, Post, User } from "../../api"
import { onMounted, ref, watch } from "vue"
import { formatDateTime } from "../../utils"


const page = ref(1)
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

const users = ref<User[]>([])
const loadUsers = async () => {
  users.value = await getUsers()
}
onMounted(async () => {
  await loadUsers()
})

watch(page, async () => {
  await loadPosts()
})
</script>
<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>