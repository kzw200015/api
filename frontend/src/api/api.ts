import axios, { AxiosError, AxiosResponse } from "axios"
import { Page, Post, Resp, User, UserLoginResponse, UserRegisterOrLoginRequest } from "./types"
import { router } from "../router"
import { ElMessage } from "element-plus"

const client = axios.create({
    baseURL: "/api"
})

client.interceptors.response.use((response: AxiosResponse) => response, async (error: AxiosError) => {
    if (error.response?.status === 401) {
        await router.push("/login")
        ElMessage.info("请登录")
    } else if (error.response?.status === 403) {
        ElMessage.warning("无权进行此操作")
    }

    throw error
})
export { client }

export const getMe = async (): Promise<User> => {
    const resp = await client.get<Resp<User>>("/users/me")

    return resp.data.data
}

export const getUserById = async (id: number): Promise<User> => {
    const resp = await client.get<Resp<User>>(`/users/${id}`)

    return resp.data.data
}

export const getUsers = async (): Promise<User[]> => {
    const resp = await client.get<Resp<User[]>>("/users")

    return resp.data.data
}

export const login = async (body: UserRegisterOrLoginRequest) => {
    await client.post<Resp<UserLoginResponse>>("/auth/login", body)
}

export const register = async (body: UserRegisterOrLoginRequest) => {
    await client.post("/auth/register", body)
}

export const logout = async () => {
    await client.post("/auth/logout")
}

export const getPosts = async (page: number, size: number): Promise<Page<Post>> => {
    const resp = await client.get<Resp<Page<Post>>>("/posts", {
        params: {
            page: page,
            size: size
        }
    })

    return resp.data.data
}

export const getPostById = async (id: number): Promise<Post> => {
    const resp = await client.get<Resp<Post>>(`/posts/${id}`)

    return resp.data.data
}