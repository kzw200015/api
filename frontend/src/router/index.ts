import { createRouter, createWebHistory } from "vue-router"
import Ip from "../views/ip/IpView.vue"
import SubParser from "../views/subParser/SubParserView.vue"

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            redirect: "/ip"
        },
        {
            path: "/ip",
            component: Ip,
            meta: { title: "IP查询" }
        }, {
            path: "/subParser",
            component: SubParser,
            meta: { title: "订阅解析" }
        }
    ]
})

router.beforeEach((to) => {
    document.title = `${to.meta.title} - 工具箱`
    return true
})

export { router }