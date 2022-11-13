<template>
  <el-container>
    <el-main>
      <el-row class="login-form-container" justify="center" align="middle">
        <el-col :md="12">
          <el-form ref="registerFormRef" :model="registerForm" label-width="150px">
            <el-form-item label="用户名">
              <el-input v-model="registerForm.username"/>
            </el-form-item>
            <el-form-item label="密码" label-width="150px">
              <el-input show-password clearable v-model="registerForm.password"/>
            </el-form-item>
            <el-form-item label="重复密码" label-width="150px">
              <el-input show-password clearable v-model="registerForm.repeatPassword"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleRegisterClick">登录</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { onMounted, reactive } from "vue"
import { login, register, UserRegisterOrLoginRequest } from "../api"
import { useRouter } from "vue-router"
import { ElMessage } from "element-plus"

const router = useRouter()

interface RegisterForm extends UserRegisterOrLoginRequest {
  repeatPassword: string
}

const registerForm = reactive<RegisterForm>({
  username: "",
  password: "",
  repeatPassword: ""
})

onMounted(() => {
  document.title = "用户登录"
})

const handleRegisterClick = async () => {
  await register(registerForm)
  ElMessage.success({ message: "注册成功" })
  await login(registerForm)
  await router.push("/admin")
}
</script>
<style scoped>
.login-form-container {
  height: 100vh;
}
</style>