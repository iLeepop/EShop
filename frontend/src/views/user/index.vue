<template>
    <el-card class="box-card">
        <el-input
        v-model="queryForm.query"
        placeholder="请输入用户昵称..."
        class="input-with-select"
        style="width: 400px"
        @keypress.enter="initUserList"
        >
            <template #append>
                <el-button :icon="Search" @click="initUserList" class="select-btn"/>
            </template>
        </el-input>
        <el-table :data="tableData" height="470px" style="width: 100%">
            <el-table-column prop="id" label="#ID" width="80" />
            <el-table-column prop="nickName" label="用户昵称" width="200" />
            <el-table-column prop="avatarUrl" label="头像" width="200" class="table-avatar">
                <template #default="scope">
                    <el-avatar shape="square" :src="scope.row.avatarUrl" />
                  </template>
            </el-table-column>
            <el-table-column prop="openid" label="openID" />
            <el-table-column prop="registerDate" label="注册日期" width="200" />
            <el-table-column prop="lastLoginDate" label="最后一次登录日期" width="200" />
        </el-table>
        <div class="pagination-container">
            <el-pagination
            v-model:current-page="queryForm.pageNum"
            v-model:page-size="queryForm.pageSize"
            :page-sizes="[10, 20, 30]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            />
        </div>
    </el-card>
</template>

<script setup>
import { Search } from '@element-plus/icons-vue'
import { ref } from 'vue'
import axios from '@/utils/axios.js'

const tableData = ref([])
const queryForm = ref({
    query: '',
    pageNum: 1,
    pageSize: 10
})
const total = ref(0)

const initUserList = async () => {
    const r = await axios.post('/admin/user/list', queryForm.value)
    tableData.value = r.data.userList
    total.value = r.data.total
}
initUserList()
const handleSizeChange = async (pageSize) => {
    queryForm.value.pageNum = 1
    queryForm.value.pageSize = pageSize
    await initUserList()
}
const handleCurrentChange = async (pageNum) => {
    queryForm.value.pageNum = pageNum
    queryForm.value.pageSize = 10
    await initUserList()
}
</script>

<style lang="less" scoped>
.box-card {
    height: 99%;
    .input-with-select {
        padding: 0 0 10px 5px;
        .select-btn {
            background-color: #79bbff;
            color: #fff;
            &:hover {
                background-color: #409eff;
            }
        }
        
    }
    :deep(.cell) {
        display: flex;
        align-items: center;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .pagination-container {
        padding: 15px 5px;
    }
}
</style>