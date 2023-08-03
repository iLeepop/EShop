<template>
    <el-card class="box-card">
        <el-input
        v-model="queryForm.query"
        placeholder="请输入订单号..."
        class="input-with-select"
        style="width: 400px"
        @keypress.enter="initOrderList"
        >
            <template #append>
                <el-button :icon="Search" @click="initOrderList" class="select-btn"/>
            </template>
        </el-input>
        <el-table :data="tableData" height="470px" style="width: 100%">
            <el-table-column fixed prop="orderNo" label="订单号" width="250" />
            <el-table-column prop="wxUserInfo" label="用户昵称" width="200" :formatter="wxUserInfoNickNameFormatter"/>
            <el-table-column prop="totalPrice" label="订单总价" width="100" />
            <el-table-column prop="status" label="订单状态" width="100" :formatter="statusFormatter"/>
            <el-table-column prop="createDate" label="订单创建日期" width="200" />
            <el-table-column prop="payDate" label="订单支付日期" width="200" />
            <el-table-column prop="consignee" label="收货人" width="80" />
            <el-table-column prop="telNumber" label="联系电话" width="150" />
            <el-table-column prop="address" label="收货地址" width="400" />
            <el-table-column fixed="right" label="操作" width="190">
                <template #default="scope">
                    <el-button link type="success" @click="handleInfo(scope.row.id)" round>详情</el-button>              
                    <el-button link type="primary" @click="handleUpdateStatus(scope.row.id,2)" round>发货</el-button>
                    <el-button link type="primary" @click="handleUpdateStatus(scope.row.id,3)" round>退货</el-button>
                    <el-button link type="danger" @click="handleDelete(scope.row.id)" :icon="Delete" circle />
                </template>
            </el-table-column>
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
    <Dialog v-model="dialogTableVisible" :id="id"></Dialog>
    
</template>

<script setup>
import { Search, Delete } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ref } from 'vue'
import axios from '@/utils/axios.js'
import Dialog from '@/views/order/components/dialog.vue'

const tableData = ref([])
const queryForm = ref({
    query: '',
    pageNum: 1,
    pageSize: 10
})
const total = ref(0)
const dialogTableVisible = ref(false)
const id = ref(-1)

const initOrderList = async () => {
    const r = await axios.post('/admin/order/list', queryForm.value)
    tableData.value = r.data.orderList
    total.value = r.data.total
}
initOrderList()

const wxUserInfoNickNameFormatter = (row) => {
    return row.wxUserInfo.nickName
}

const statusFormatter = (row) => {
    switch (row.status) {
        case 1:
            return '未支付'
        case 2:
            return '待收货'
        case 3:
            return '退款/退货'
        default:
            return '未知状态'
    }
}

//Operation
const handleInfo = async (orderId) => {
    id.value = orderId
    dialogTableVisible.value = true
}

const handleUpdateStatus = (id, status) => {
    ElMessageBox.confirm(
    '您确定要更新此订单状态吗？',
    '系统提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async () => {
        const r = await axios.post('/admin/order/updateStatus', { id, status })
        if(r.data.code == 0){
            ElMessage({
                type: 'success',
                message: '更新成功',
            })
            initOrderList()
        }else{
            ElMessage({
                type: 'error',
                message: e.data.msg,
            })
        }
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '更新已取消',
      })
    })
}

const handleDelete = (id) => {
    ElMessageBox.confirm(
    '您确定要删除此订单吗？',
    '系统提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async () => {
        const r = await axios.get('/admin/order/delete/' + id)
        if(r.data.code == 0){
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            initOrderList()
        }else{
            ElMessage({
                type: 'error',
                message: e.data.msg,
            })
        }
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除已取消',
      })
    })
}

//Pagination
const handleSizeChange = async (pageSize) => {
    queryForm.value.pageNum = 1
    queryForm.value.pageSize = pageSize
    await initOrderList()
}
const handleCurrentChange = async (pageNum) => {
    queryForm.value.pageNum = pageNum
    queryForm.value.pageSize = 10
    await initOrderList()
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
    .pagination-container {
        padding: 15px 5px;
    }
}
</style>