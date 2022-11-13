<template>
  <el-container>
    <el-main>
      <el-row class="login-form-container" justify="center" align="middle">
        <el-col :md="12">
          <el-form :model="loginForm" label-width="150px">
            <el-form-item label="用户名">
              <el-input v-model="loginForm.username"/>
            </el-form-item>
            <el-form-item label="密码" label-width="150px">
              <el-input show-password clearable v-model="loginForm.password"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleLoginClick">登录</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { onMounted, reactive } from "vue"
import { login, UserRegisterOrLoginRequest } from "../api"
import { useRouter } from "vue-router"
import { ElMessage } from "element-plus"

const props = defineProps<{ redirectUrl?: string }>()
const router = useRouter()

type LoginForm = UserRegisterOrLoginRequest

const loginForm = reactive<LoginForm>({
  username: "",
  password: ""
})

onMounted(() => {
  document.title = "用户登录"
})

const handleLoginClick = async () => {
  await login(loginForm)
  ElMessage.success({ message: "登录成功" })
  if (props.redirectUrl) {
    await router.push(props.redirectUrl)
  } else {
    await router.push("/admin")
  }
}
</script>
<style scoped>
.login-form-container {
  height: 100%;
}
</style>