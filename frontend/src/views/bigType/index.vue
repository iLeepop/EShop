<template>
    <el-card class="box-card">
        <el-input
        v-model="queryForm.query"
        placeholder="请输入商品大类名称..."
        class="input-with-select"
        style="width: 400px"
        @keypress.enter="initBigTypeList"
        >
            <template #append>
                <el-button :icon="Search" @click="initBigTypeList" class="select-btn"/>
            </template>
        </el-input>
        <el-button :icon="Plus" @click="handleDialog()" class="add-btn">添加商品大类</el-button>
        <el-table :data="tableData" height="470px" style="width: 100%">
            <el-table-column prop="id" label="#ID" width="80" />
            <el-table-column prop="name" label="商品大类名称" width="110" />
            <el-table-column prop="image" label="商品大类图片" width="110" >
                <template #default="{ row }">
                    <img :src="getServiceUrl() + '/image/bigtype/' + row.image" alt="" style="width: 80px; height: 80px" />
                </template>
            </el-table-column>
            <el-table-column prop="remark" label="商品大类描述"/>
            <el-table-column fixed="right" label="操作" width="200" >
                <template #default="scope">
                    <el-button link type="success" @click="handleEditImage(scope.row)" round>更改图片</el-button>
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
    <Dialog v-model="dialogTableVisible" :dialogTable="dialogTableVisible" :id="id" :dialogTitle="dialogTitle" @initBigTypeList="initBigTypeList"></Dialog>
    <ImageDialog v-model="imageDialogTableVisible" :value="value" @initBigTypeList="initBigTypeList"></ImageDialog>
</template>

<script setup>
import { Search,Edit, Delete, Plus } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios, { getServiceUrl } from '@/utils/axios.js'
import Dialog from '@/views/bigType/components/dialog.vue'
import ImageDialog from '@/views/bigType/components/imagedialog.vue'

const tableData = ref([])
const queryForm = ref({
    query: '',
    pageNum: 1,
    pageSize: 10
})
const total = ref(0)
const dialogTableVisible = ref(false)
const imageDialogTableVisible = ref(false)
const dialogTitle = ref('')
const id = ref(-1)
const value = ref({})

const initBigTypeList = async () => {
    const r = await axios.post('/admin/bigType/list', queryForm.value)
    tableData.value = r.data.bigTypeList
    total.value = r.data.total
}
initBigTypeList()

const handleDialog = (typeId) => {
    if(typeId) {
        console.log('handleEdit')
        id.value = typeId
        dialogTitle.value = '编辑商品大类'
        dialogTableVisible.value = true
    } else {
        console.log('handleBigTypeAdd')
        id.value = -1
        dialogTitle.value = '添加商品大类'
        dialogTableVisible.value = true
    }
    
}

const handleEditImage= (row) => {
    imageDialogTableVisible.value = true
    value.value = JSON.parse(JSON.stringify(row))
}

const handleDelete = (id) => {
    ElMessageBox.confirm(
    '您确定要删除此大类吗？',
    '系统提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
        const r = await axios.get('/admin/bigType/delete/' + id)
        if(r.data.code == 0){
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            initBigTypeList()
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

const handleSizeChange = async (pageSize) => {
    queryForm.value.pageNum = 1
    queryForm.value.pageSize = pageSize
    await initBigTypeList()
}
const handleCurrentChange = async (pageNum) => {
    queryForm.value.pageNum = pageNum
    queryForm.value.pageSize = 10
    await initBigTypeList()
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
    .add-btn {
        margin: 0 0 10px 5px;
    }
    .pagination-container {
        padding: 15px 5px;
    }
}
</style>