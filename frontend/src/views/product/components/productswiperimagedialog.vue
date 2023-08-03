<template>
    <el-dialog 
        model-value="productSwiperImageDialogTableVisible" 
        :show-close="false" 
        :title="幻灯图切换"
        width="40%">
        <template #header="{ titleId, titleClass }">
            <div class="my-header">
            <h4 :id="titleId" :class="titleClass">幻灯图切换</h4>
            <el-button link type="danger" @click="close">
                <el-icon :size="25" class="el-icon--left"><CircleCloseFilled /></el-icon>
            </el-button>
            </div>
        </template>
        <el-form 
            :model="formData" 
            ref="formRef" 
            :rules="rules" 
            label-width="100px" 
            >
            <el-form-item label="排列序号" prop="sort">
                <el-input v-model="formData.sort" style="width: 100px"/>
            </el-form-item>
            <el-upload
                class="image-uploader"
                :headers="headers"
                :action="getServiceUrl() + '/admin/productSwiperImg/uploadImage'"
                :show-file-list="false"
                :on-success="handleImageSuccess"
                :before-upload="beforeImageUpload"
            >
                <img v-if="imageUrl" :src="imageUrl" class="image" />
                <el-icon v-else class="image-uploader-icon"><Plus /></el-icon>
            </el-upload>
            <el-form-item>
                <el-button type="primary" @click="handleConfirm">确认更换</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column type="index" width="50" />
            <el-table-column prop="image" label="轮播图片">
                <template v-slot="scope">
                    <img :src="getServiceUrl()+'/image/productSwiperImgs/'+scope.row.image"
                    width="80" height="80"/>
                </template>
            </el-table-column>
            <el-table-column prop="sort" label="排列序号" width="100"/>
            <el-table-column prop="action" label="操作" width="100" >
                <template v-slot="scope">
                    <el-button type="danger" :icon="Delete"
                    @click="handleDelete(scope.row.id)"></el-button>
                </template>
            </el-table-column>
        </el-table>    
    </el-dialog>
</template>

<script setup>
import { ref, defineEmits, defineProps, watch } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { CircleCloseFilled, Plus, Delete } from '@element-plus/icons-vue'
import axios, { getServiceUrl } from '@/utils/axios.js'

const emits = defineEmits(['update:modelValue'])
const props = defineProps({
    value: {
        type: Object,
        default: () => {},
        required: true
    },
    productSwiperImage: {
        type: Boolean,
        default: false,
        required: true
    }
})
const tableData = ref([])
const formData = ref({
    productId: -1,
    sort: 0,
    image: ''
})
const imageUrl = ref('')

const formRef = ref(null)

const headers = ref({
    token: window.sessionStorage.getItem('token')
})
const rules = ref({
    sort: [
        {
            required: true,
            message: '请输入排列序号',
        },
        {
            type: 'number',
            message: '排列序号必须为数字值',
            transform: (value) => Number(value)
        }
    ]
})

const initSwiperList = async () => {
    const res=await axios.get("admin/productSwiperImg/list/" + formData.value.productId);
    tableData.value=res.data.list
}

watch(
    () => props.productSwiperImage,
    () => {
        formData.value = {
            productId: -1,
            sort: 0,
            image: ''
        }
        imageUrl.value = ''
        formData.value.productId = props.value.id
        initSwiperList()
    },
    {
        deep: true,
        immediate: true
    }
)

const handleImageSuccess = (res) => {
    imageUrl.value = getServiceUrl() + res.data.src
    formData.value.image = res.data.title
}

const beforeImageUpload = (file) => {
    const isJPG = file.type === 'image/jpeg'
    const isLt2M = file.size / 1024 / 1024 < 2
    if (!isJPG) {
        ElMessage.error('图片必须是jpg格式')
    }
    if (!isLt2M) {
        ElMessage.error('图片大小不能超过2M!')
    }
    return isJPG && isLt2M
}

const close = () => {
    emits('update:modelValue', false)
}
const handleConfirm = () => {
    formRef.value.validate(async(valid)=>{
        if(valid){
            let result=await axios.post("admin/productSwiperImg/save",formData.value)
            let data=result.data;
            if(data.code==0){
                ElMessage.success("执行成功！");
                formRef.value.resetFields();
                imageUrl.value = ''
                initSwiperList();
            }else{
                ElMessage.error(data.msg);
            }
        }
    })

}
const handleDelete = (id) => {
    ElMessageBox.confirm(
    '您确定要删除这条记录吗？',
    '系统提示',
    {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
    }).then(async() => {
        let res=await axios.get('admin/productSwiperImg/delete/'+id)
        if(res.data.code==0){
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            initSwiperList()
        }else{
            ElMessage({
                type: 'error',
                message: res.data.msg,
            })
        }
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '已取消删除',
        })
    })
}
</script>

<style lang="less" scoped>
.my-header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
.image-uploader {
    text-align: center;
}
.image-uploader .image {
    width: 178px;
    height: 178px;
    display: block;
}
:deep(.el-upload) {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: border-color .3s cubic-bezier(.645,.045,.355,1);
}

:deep(.el-upload:hover) {
    border-color: #c0c4cc;
  }
  
.el-icon.image-uploader-icon {
    font-size: 28px;
    color: #3086ff;
    width: 178px;
    height: 178px;
    text-align: center;
}
</style>