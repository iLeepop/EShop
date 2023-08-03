<template>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-vertical-demo"
        :unique-opened="true"
        text-color="gary"
        :router="true"
      >
        <el-menu-item index="home">
            <el-icon><House /></el-icon>
            <span>首页</span>
        </el-menu-item>
        <el-menu-item index="user">
            <el-icon><Operation /></el-icon>
            <span>用户管理</span>
        </el-menu-item>
        <el-sub-menu index="type">
            <template #title>
                <el-icon><SetUp /></el-icon>
                <span>商品类型管理</span>
            </template>
            <el-menu-item index="bigType">
                <el-icon><Help /></el-icon>
                商品大类管理
            </el-menu-item>
            <el-menu-item index="smallType">
                <el-icon><Orange /></el-icon>
                商品小类管理
            </el-menu-item>
        </el-sub-menu>
        <el-menu-item index="product">
            <el-icon><Goods /></el-icon>
            <span>商品管理</span>
        </el-menu-item>
        <el-menu-item index="order">
            <el-icon><Tickets /></el-icon>
            <span>订单管理</span>
          </el-menu-item>
          <el-sub-menu index="system">
            <template #title>
                <el-icon><Setting /></el-icon>
                <span>系统管理</span>
            </template>
            <el-menu-item index="modifyPassword">
                <el-icon><EditPen /></el-icon>
                <span>修改密码</span>
            </el-menu-item>
            <el-menu-item>
                <el-icon><SwitchButton /></el-icon>
                <span @click="logout">安全退出</span>
            </el-menu-item>
          </el-sub-menu>
      </el-menu>
</template>

<script setup>
import { ref, watch } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { SwitchButton, EditPen, Setting, Tickets, Goods, SetUp, Help, Orange, House, Operation } from '@element-plus/icons-vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'

const route = useRoute()
const activeIndex = ref('home')
const initActiveIndex = () => {
    let list = route.matched
    if (list.length > 0) {
        activeIndex.value = list[list.length - 1].path.replace('/', '')
    }
}
watch(route, () => {
    initActiveIndex()
}, {deep: true, immediate: true})

const store = useStore()
const logout = () => {
    ElMessageBox.confirm(
    '您确定退出吗？',
    '系统提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
        store.dispatch('logout') // 清除登录信息
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消',
      })
    })
}
</script>

<style lang="less" scoped>

</style>