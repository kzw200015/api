<template>
  <div class="post-list-container">
    <el-table height="100%" :data="posts">
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
          {{ scope.row.user.username }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="handleClickEdit(scope.row.id)">编辑</el-button>
          <el-button type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background class="pager" v-model:current-page="page" layout="prev,pager,next" :total="total"/>
  </div>
</template>

<script setup lang="ts">
import { getPosts, Post } from "../../api"
import { onMounted, ref, watch } from "vue"
import { formatDateTime } from "../../utils"
import { useRouter } from "vue-router"


const page = ref(1)
const size = ref(20)
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

watch(page, async () => {
  await loadPosts()
})

const router = useRouter()
const handleClickEdit = async (postId: number) => {
  console.log(postId)
  await router.push({ name: "postEdit", params: { postId: postId } })
}
</script>
<style scoped>
.post-list-container {
  height: 100%;
  display: grid;
  grid-template-rows: 1fr auto;
}

.pager {
  height: 50px;
  justify-content: center;
}
</style>