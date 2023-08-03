<template>
    <div class="app-wrapper">
        <el-container>
            <el-aside width="200px" class="sider-container"><Menu/></el-aside>
            <el-container>
                <el-header class="head-container">
                    <div class="navbar">
                        <Breadcrumb/>
                        <Avatar/>
                    </div>
                </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
                <el-footer><Footer/></el-footer>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import Menu from '@/views/layout/menu'
import Breadcrumb from '@/views/layout/header/breadcrumb.vue'
import Avatar from '@/views/layout/header/avatar.vue'
import Footer from '@/views/layout/footer'

</script>

<style lang="less" scoped>
.app-wrapper {
    position: relative;
    width: 100%;
    height: 100vh;
    .sider-container {
        background-color: #fff;
        height: 100%;
        border-right: 1px solid #ebeef5;
    }
    .head-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 56px;
        border-bottom: 1px solid #ebeef5;
        box-shadow: 0 0 1px 0 rgba(0,0,0,.12);
        .navbar {
            display: flex;
            width: 100%;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            overflow: hidden;
            box-sizing: border-box;
        }
    }
}

:deep(.el-container) {
    height: 100%;
}

:deep(ul.el-menu) {
    border-right-width: 0;
}
</style>