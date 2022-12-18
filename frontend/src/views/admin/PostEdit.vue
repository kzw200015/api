<template>
  <markdown-editor v-model:content="post.content"></markdown-editor>
  <el-button>保存</el-button>
</template>

<script lang="ts" setup>
import { onMounted, ref, watch } from "vue"
import { getPostById, Post } from "../../api"
import "vditor/dist/index.css"
import MarkdownEditor from "../../components/MarkdownEditor.vue"

const props = defineProps<{
  postId: string
}>()
const post = ref<Post>({ content: "", createTime: 0, id: 0, title: "", updateTime: 0, userId: 0 })
onMounted(async () => {
  post.value = await getPostById(Number.parseInt(props.postId))
})

watch(() => post.value.content, () => console.log(post.value))


// const handleSave = async () => {
//
// }
</script>
<style scoped>

</style>