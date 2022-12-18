<template>
  <div id="editor"></div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch } from "vue"
import Vditor from "vditor"

const props = defineProps<{
  content?: string
}>()

const emit = defineEmits<{
  (e: "update:content", content: string): void
}>()

const vditor = ref<Vditor>()
onMounted(() => {
  vditor.value = new Vditor("editor", {
    after: async () => {
      vditor.value?.setValue(props.content ?? "")
      watch(() => props.content, (value) => {
        vditor.value?.setValue(value ?? "")
      })
    },
    input: (value: string) => {
      emit("update:content", value)
    }
  })
})

</script>
<style scoped>

</style>