export interface UserRegisterOrLoginRequest {
    username: string
    password: string
}

export interface UserLoginResponse {
    token: string
}

export interface User {
    id: number
    username: string
}

export interface Post {
    id: number
    title: string
    content: string
    createTime: number
    updateTime: number
    userId: number
    user?: User
}

export interface Page<T> {
    total: number
    pages: number
    pageSize: number
    pageNum: number
    values: T[]
}

export interface Resp<T> {
    status: number
    message: string
    data: T
}