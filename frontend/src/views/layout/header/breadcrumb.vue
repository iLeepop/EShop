<template>
    <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="(item, index) in breadcrumbList" :key="index">
            <span class="no-redirect" v-if="index == breadcrumbList.length-1">{{item.name}}</span>
            <span class="redirect" @click="handleRedirect(item.path)" v-else>{{item.name}}</span>
        </el-breadcrumb-item>
    </el-breadcrumb>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
const route = useRoute()
const router = useRouter()
const breadcrumbList = ref([])

const handleRedirect = (path) => {
    router.push(path)
}
const initBreadcrumbList = () => {
    breadcrumbList.value = route.matched;
}
watch(route, (to, from) => {
    initBreadcrumbList();
}, {deep: true, immediate: true})
</script>

<style lang="less" scoped>
.no-redirect {
    cursor: text;
}
.redirect {
    color: #666;
    font-weight: 600;
    cursor: pointer;
    &:hover {
        color: #409eff;
    }
}
</style>