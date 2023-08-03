<template>
    <el-dialog 
        model-value="dialogTableVisible" 
        :show-close="false" 
        :title="dialogTitle"
        width="40%">
        <template #header="{ titleId, titleClass }">
            <div class="my-header">
            <h4 :id="titleId" :class="titleClass">{{dialogTitle}}</h4>
            <el-button link type="danger" @click="close">
                <el-icon :size="25" class="el-icon--left"><CircleCloseFilled /></el-icon>
            </el-button>
            </div>
        </template>
        <el-form 
            :model="formData" 
            :rules="rules" 
            ref="formRef" 
            label-width="100px" 
            >
            <el-form-item label="小类名称" prop="name">
                <el-input v-model="formData.name"></el-input>
            </el-form-item>

            <el-form-item label="所属大类">
                <el-select v-model="formData.bigType.id">
                    <el-option
                        v-for="item in bigTypeSelectOptions"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="小类描述" prop="remark">
                <el-input :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" v-model="formData.remark"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <el-button type="primary" @click="handleConfirm">提交</el-button>
            <el-button @click="close">取消</el-button>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref, defineEmits, defineProps, watch } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { CircleCloseFilled } from '@element-plus/icons-vue'
import axios from '@/utils/axios.js'

const emits = defineEmits(['update:modelValue', 'initSmallTypeList'])
const props = defineProps({
    id: {
        type: Number,
        default: -1,
        required: true
    },
    dialogTitle: {
        type: String,
        default: '',
        required: true
    },
    dialogTable: {
        type: Boolean,
        default: false,
        required: true
    }
})
watch(
    () => props.dialogTable,
    () => {
        let id = props.id
        if(id != -1){
            initFormData(id)
        }else{
            formData.value = {
                id: -1,
                name: '',
                remark: '',
                bigType: {
                    id: ''
                }
            }
        }
        initBigTypeSelectList()
    }
)

const formData = ref({
    id: -1,
    name: '',
    remark: '',
    bigType: {
        id: ''
    }
})
const rules = ref({
    name: [
        { required: true, message: '请输入大类名称', trigger: 'blur' }
    ],
    remark: [
        { required: true, message: '请输入大类描述', trigger: 'blur' }
    ]
})
const formRef = ref(null)
const bigTypeSelectOptions = ref([])

const initFormData = async (id) => {
    const r = await axios.get('/admin/smallType/' + id)
    formData.value = r.data.smallType
}
const initBigTypeSelectList = async () => {
    const r = await axios.get('/admin/bigType/listAll')
    bigTypeSelectOptions.value = r.data.bigTypeList
}
const close = () => {
    emits('update:modelValue', false)
}
const handleConfirm = () => {
    formRef.value.validate( (valid) => {
        if (valid) {
            if(formData.value.bigType.id == ''){
                ElMessage({
                    type: 'warning',
                    message: '请选择所属大类'
                })
                return
            }
            ElMessageBox.confirm('确认提交吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                const r = await axios.post('/admin/smallType/save', formData.value)
                if(r.data.code == 0){
                    ElMessage({
                        type: 'success',
                        message: '提交成功'
                    })
                    formRef.value.resetFields()
                    emits('initSmallTypeList')
                    close()
                }else{
                    ElMessage({
                        type: 'error',
                        message: r.data.msg
                    })
                }
            }).catch(() => {
                ElMessage({
                    type: 'info',
                    message: '已取消提交'
                })
            })
        } else {
            ElMessage({
                    type: 'info',
                    message: '请检查表单'
                })
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
</style>