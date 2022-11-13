import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios"
import { Page, Post, Resp, User, UserLoginResponse, UserRegisterOrLoginRequest } from "./types"
import { ElMessage } from "element-plus"
import { router } from "../router"

const client = axios.create({
    baseURL: "/api"
})

client.interceptors.request.use((config: AxiosRequestConfig) => {
    if (config.headers) {
        config.headers.Token = localStorage.getItem("token")
    } else {
        config.headers = { Token: localStorage.getItem("token") }
    }
    return config
})

client.interceptors.response.use((response: AxiosResponse) => response, async (error: AxiosError) => {
    if (error.response?.status === 401) {
        await router.push("/login")
        ElMessage.info("请登录")
    } else if (error.response?.status === 403) {
        ElMessage.warning("无权进行此操作")
    }

    return error
})
export { client }

export const getMe = async () => {
    const resp = await client.get<Resp<User>>("/users/me")

    return resp.data.data
}

export const getUserById = async (id: number) => {
    const resp = await client.get<Resp<User>>(`/users/${id}`)

    return resp.data.data
}

export const login = async (body: UserRegisterOrLoginRequest) => {
    const resp = await client.post<Resp<UserLoginResponse>>("/auth/login", body)
    localStorage.setItem("token", resp.data.data.token)
}

export const register = async (body: UserRegisterOrLoginRequest) => {
    await client.post("/auth/register", body)
}

export const logout = async () => {
    await client.post("/auth/logout")
}

export const getPosts = async (page: number, size: number) => {
    const resp = await client.get<Resp<Page<Post>>>("/posts", {
        params: {
            page: page,
            size: size
        }
    })

    return resp.data.data
}