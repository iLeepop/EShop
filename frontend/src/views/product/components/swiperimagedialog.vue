<template>
    <el-dialog 
        model-value="swiperImageDialogTableVisible" 
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
            <el-form-item label="排列序号" prop="swiperSort">
                <el-input v-model="formData.swiperSort" style="width: 100px"/>
                </el-form-item>
            <el-upload
                class="image-uploader"
                :headers="headers"
                :action="getServiceUrl() + '/admin/product/uploadSwiperImage'"
                :show-file-list="false"
                :on-success="handleImageSuccess"
                :before-upload="beforeImageUpload"
            >
                <img v-if="imageUrl" :src="imageUrl" class="image" />
                <el-icon v-else class="image-uploader-icon"><Plus /></el-icon>
            </el-upload>
        </el-form>
        <template #footer>
            <el-button type="primary" @click="handleConfirm">确认更换</el-button>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref, defineEmits, defineProps, watch } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { CircleCloseFilled, Plus } from '@element-plus/icons-vue'
import axios, { getServiceUrl } from '@/utils/axios.js'

const emits = defineEmits(['update:modelValue', 'initProductList'])
const props = defineProps({
    id: {
        type: Number,
        default: -1,
        required: true
    },
    value: {
        type: Object,
        default: () => {},
        required: true
    }
})

const formData = ref({
    id: -1,
    proPic: '',
    swiperSort: 0,
    swiperPic: ''
})
const imageUrl = ref('')

const formRef = ref(null)

const headers = ref({
    token: window.sessionStorage.getItem('token')
})

const rules = ref({
    swiperSort: [
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

watch(
    () => props.value,
    () => {
        formData.value = props.value
        imageUrl.value = getServiceUrl() + '/image/swiper/' + formData.value.swiperPic
    },
    {
        deep: true,
        immediate: true
    }
)
const handleImageSuccess = (res) => {
    imageUrl.value = getServiceUrl() + res.data.src
    formData.value.swiperPic = res.data.title
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
const handleConfirm = async () => {
    formRef.value.validate(async (valid) => {
        if (valid) {
            console.log(formData.value);
            let r = await axios.post('/admin/product/save', formData.value)
            let data = r.data
            if(data.code == 0) {
                ElMessage.success('保存成功')
                emits('initProductList')
                close()
            } else {
                ElMessage.error(data.msg)
            }
        } else {
            ElMessage.error(data.msg)
        }
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