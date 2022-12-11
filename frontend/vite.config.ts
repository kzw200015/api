import { defineConfig } from "vite"
import vue from "@vitejs/plugin-vue"
import eslint from "vite-plugin-eslint"
import AutoImport from "unplugin-auto-import/vite"
import Components from "unplugin-vue-components/vite"
import ElementPlus from "unplugin-element-plus/vite"
import { AntDesignVueResolver, ElementPlusResolver, NaiveUiResolver } from "unplugin-vue-components/resolvers"

export default defineConfig({
    server: {
        proxy: {
            "/api": { target: "http://localhost:8080" }
        }
    },
    plugins: [
        ElementPlus(),
        vue(),
        eslint(),
        AutoImport({
            resolvers: [ElementPlusResolver(), NaiveUiResolver(),AntDesignVueResolver()]
        }),
        Components({
            resolvers: [ElementPlusResolver(), NaiveUiResolver(),AntDesignVueResolver()]
        })
    ]
})
