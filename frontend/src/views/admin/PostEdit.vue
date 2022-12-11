<template>
  <div>post edit</div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue"
import { getPostById, Post } from "../../api"

const props = defineProps<{
  postId: number
}>()

const post = ref<Post>()

onMounted(async () => {
  await getPostById(props.postId)
})
</script>
<style scoped>

</style>