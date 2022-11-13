import { createRouter, createWebHashHistory } from "vue-router"

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: "/login",
            component: () => import("../views/UserLogin.vue"),
            props: true
        },
        {
            path: "/register",
            component: () => import("../views/UserRegister.vue")
        },
        {
            path: "/admin",
            component: () => import("../views/admin/AdminBase.vue"),
            redirect: "/admin/posts",
            beforeEnter: (to) => {
                if (localStorage.getItem("token")) {
                    document.title = `${to.meta.title} - 后台管理`
                    return true
                } else {
                    return { path: "/login", query: { redirectUrl: to.fullPath } }
                }
            },
            children: [
                {
                    path: "posts",
                    component: () => import("../views/admin/PostList.vue"),
                    meta: { title: "文章列表" }
                },
                {
                    path: "posts/:id/edit",
                    component: () => import("../views/admin/PostEdit.vue"),
                    meta: { title: "文章编辑" },
                    props: true
                }
            ]
        }
    ]
})


export { router }