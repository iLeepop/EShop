<template>
    <el-dropdown class="el-dropdown-link avatar-container">
        <span class="avatar">
            <el-avatar
            shape="square"
            :src="squareUrl">
            </el-avatar>
        </span>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item @click="logout">安全退出</el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>

<script setup>
//https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useStore } from 'vuex';

const squareUrl = ref("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png")
const store = useStore();
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
.avatar-container {
    padding-right: 20px;
    cursor: pointer;
    .avatar {
        &:hover {
            border: 1px solid #ebeef5;
        }
    }
}
</style>