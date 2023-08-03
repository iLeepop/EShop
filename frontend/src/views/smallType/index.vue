<template>
    <el-card class="box-card">
        <el-input
        v-model="queryForm.query"
        placeholder="请输入商品小类名称..."
        class="input-with-select"
        style="width: 400px"
        @keypress.enter="initBigTypeList"
        >
            <template #append>
                <el-button :icon="Search" @click="initSmallTypeList" class="select-btn"/>
            </template>
        </el-input>
        <el-button :icon="Plus" @click="handleDialog()" class="add-btn">添加商品小类</el-button>
        <el-table :data="tableData" height="470px" style="width: 100%">
            <el-table-column prop="id" label="#ID" width="80" />
            <el-table-column prop="name" label="商品小类名称" width="130" />
            <el-table-column prop="bigType" label="所属大类" width="130" :formatter="bigTypeFormatter"/>
            <el-table-column prop="remark" label="商品小类描述"/>
            <el-table-column fixed="right" label="操作" width="200" >
                <template #default="scope">
                    <el-button link type="primary" @click="handleDialog(scope.row.id)" :icon="Edit" circle />
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
    <Dialog 
        v-model="dialogTableVisible" 
        :dialogTable="dialogTableVisible" 
        :id="id" :dialogTitle="dialogTitle" 
        @initSmallTypeList="initSmallTypeList">
    </Dialog>
</template>

<script setup>
import { Search,Edit, Delete, Plus } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from '@/utils/axios.js'
import Dialog from '@/views/smallType/components/dialog.vue'
//定义数据
const tableData = ref([])
const queryForm = ref({
    query: '',
    pageNum: 1,
    pageSize: 10
})
const total = ref(0)
const dialogTableVisible = ref(false)
const dialogTitle = ref('')
const id = ref(-1)
//定义方法
const initSmallTypeList = async () => {
    const r = await axios.post('/admin/smallType/list', queryForm.value)
    tableData.value = r.data.smallList
    total.value = r.data.total
}
initSmallTypeList()

//弹出窗口
const handleDialog = (typeId) => {
    if(typeId) {
        console.log('handleEdit')
        id.value = typeId
        dialogTitle.value = '编辑商品小类'
    } else {
        console.log('handleBigTypeAdd')
        id.value = -1
        dialogTitle.value = '添加商品小类'
    }
    dialogTableVisible.value = true
}
//删除
const handleDelete = (id) => {
    ElMessageBox.confirm(
    '您确定要删除此小类吗？',
    '系统提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
        const r = await axios.get('/admin/smallType/delete/' + id)
        if(r.data.code == 0){
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            initSmallTypeList()
        }else{
            ElMessage({
                type: 'error',
                message: r.data.msg,
            })
        }
    }).catch((err) => {
        console.log(err);
      ElMessage({
        type: 'info',
        message: '删除已取消',
      })
    })
}

const bigTypeFormatter = (row) => {
    return row.bigType.name
}
//分页 每页条数
const handleSizeChange = async (pageSize) => {
    queryForm.value.pageNum = 1
    queryForm.value.pageSize = pageSize
    await initSmallTypeList()
}
//分页 页码
const handleCurrentChange = async (pageNum) => {
    queryForm.value.pageNum = pageNum
    queryForm.value.pageSize = 10
    await initSmallTypeList()
}
</script>

<style lang="less" scoped>

</style>