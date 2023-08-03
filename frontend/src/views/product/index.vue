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
                <el-button :icon="Search" @click="initProductList" class="select-btn"/>
            </template>
        </el-input>
        <el-button :icon="Plus" @click="handleAddEdit()" class="add-btn">添加商品</el-button>
        <el-table :data="tableData" height="470px" style="width: 100%">
            <el-table-column prop="name" label="商品名称" width="250" fixed/>
            <el-table-column prop="image" label="商品图片" width="150">
                <template v-slot="scope">
                    <img :src="getServiceUrl()+'/image/hotImage/'+scope.row.proPic"
                    width="80" height="80"/>
                </template>
            </el-table-column>
            <el-table-column prop="price" label="商品名称" width="100" />
            <el-table-column prop="stock" label="商品库存" width="100" />
            <el-table-column prop="type" label="商品类别" width="200" :formatter="typeNameFormatter"/>
            <el-table-column prop="hot" label="热卖" width="100" align="center">
                <template v-slot="{row}">
                    <el-switch v-model="row.hot" @change="hotChangeHandle(row)"></el-switch>
                </template>
            </el-table-column>
            <el-table-column prop="swiper" label="首页幻灯" width="100" align="center">
                <template v-slot="{row}">
                    <el-switch v-model="row.swiper" @change="swiperChangeHandle(row)"></el-switch>
                </template>
            </el-table-column>
            <el-table-column prop="swiperPic" label="幻灯图片" width="150" align="center">
                <template v-slot="scope">
                    <img :src="getServiceUrl()+'/image/swiper/'+scope.row.swiperPic"
                    width="150" height="75"/>
                </template>
            </el-table-column>
            <el-table-column prop="swiperSort" label="幻灯排序" width="100" align="center"/>
            <el-table-column prop="description" label="商品描述" width="400"/>
            <el-table-column prop="action" label="操作" width="340" fixed="right">
                <template v-slot="scope">
                    <el-button link type="success" @click="handleEditImage(scope.row)">更换图片</el-button>
                    <el-button link type="primary" @click="handleEditSwiper(scope.row)">幻灯设置</el-button>
                    <el-button link type="primary" @click="handleEditProductSwiper(scope.row)">轮播图设置</el-button>
                    <el-button link type="primary" :icon="Edit" @click="handleAddEdit(scope.row.id)"></el-button>
                    <el-button link type="danger" :icon="Delete" @click="handleDelete(scope.row.id)"></el-button>
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
    <ImageDialog 
        v-model="imageDialogVisible" 
        :value="value" 
        @initProductList="initProductList" />
    <SwiperImageDialog 
        v-model="swiperImageDialogTableVisible" 
        :value="value" 
        @initProductList="initProductList" />
    <ProductSwiperImageDialog 
        v-model="productSwiperImageDialogTableVisible"
        :productSwiperImage="productSwiperImageDialogTableVisible" 
        :value="value" />
    <AddEditdialog
        v-model="AddEditDialogVisible"
        :AddEditDialog="AddEditDialogVisible"
        :id="id" 
        :dialogTitle="dialogTitle"
        @initProductList="initProductList" />
</template>

<script setup>
import { Search, Delete, Edit, Plus } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ref } from 'vue'
import axios, { getServiceUrl } from '@/utils/axios.js'
import ImageDialog from '@/views/product/components/imagedialog.vue'
import SwiperImageDialog from '@/views/product/components/swiperimagedialog.vue'
import ProductSwiperImageDialog from '@/views/product/components/productswiperimagedialog.vue'
import AddEditdialog from '@/views/product/components/AddEditdialog.vue'

const queryForm=ref({
    query:'',
    pageNum:1,
    pageSize:10
})
const total=ref(0)
const tableData =ref([])
const id=ref(-1)
const dialogTitle=ref('')
const dialogVisible=ref(false)
const imageDialogVisible=ref(false)
const swiperImageDialogTableVisible=ref(false)
const productSwiperImageDialogTableVisible=ref(false)
const AddEditDialogVisible=ref(false)
const value = ref({})

const handleAddEdit=(productId)=>{
    if(productId){
        id.value=productId;
        dialogTitle.value="商品修改"
    }else{
        id.value=-1;
        dialogTitle.value="商品添加"
    }
    AddEditDialogVisible.value=true
}
const initProductList=async()=>{
    const res=await axios.post("admin/product/list",queryForm.value);
    tableData.value=res.data.productList;
    total.value=res.data.total;
}
initProductList();

//每页条数切换
const handleSizeChange = (pageSize) => {
    queryForm.value.pageNum=1;
    queryForm.value.pageSize=pageSize;
    initProductList();
}
//页码切换
const handleCurrentChange = (pageNum) => {
    queryForm.value.pageNum=pageNum;
    initProductList();
}
//更改图片
const handleEditImage= (row) => {
    imageDialogVisible.value = true
    value.value = JSON.parse(JSON.stringify(row))
}
//幻灯设置
const handleEditSwiper = (row) => {
    swiperImageDialogTableVisible.value = true
    value.value = JSON.parse(JSON.stringify(row))
}
//商品轮播图设置
const handleEditProductSwiper = (row) => {
    productSwiperImageDialogTableVisible.value = true
    value.value = JSON.parse(JSON.stringify(row))
}
//格式处理
const typeNameFormatter=(row)=>{
    return row.smallType.name
}
//热门切换
const hotChangeHandle = async (row) => {
    let r = await axios.get('admin/product/updateHot/' + row.id + '/state/' + row.hot)
    if(r.data.code == 0) {
        ElMessage({
            type: 'success',
            message: '执行成功'
        })
        initProductList()
    }else {
        ElMessage({
            type: 'error',
            message: r.data.msg
        })
    }
}
//幻灯切换
const swiperChangeHandle = async (row) => {
    let r = await axios.get('admin/product/updateSwiper/' + row.id + '/state/' + row.swiper)
    if(r.data.code == 0) {
        ElMessage({
            type: 'success',
            message: '执行成功'
        })
        initProductList()
    }else {
        ElMessage({
            type: 'error',
            message: r.data.msg
        })
    }
}
//删除
const handleDelete=(id)=>{
    ElMessageBox.confirm(
    '您确定要删除这条记录吗？',
    '系统提示',
    {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    }).then(async() => {
        let res=await axios.get('admin/product/delete/'+id)
            if(res.data.code==0){
                ElMessage({
                    type: 'success',
                    message: '删除成功'
                })
                initProductList();
            }else{
                ElMessage({
                    type: 'error',
                    message: res.data.msg,
                })
            }
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消删除'
            })
        })
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