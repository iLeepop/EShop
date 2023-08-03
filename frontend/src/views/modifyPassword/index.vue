<template>
    <el-card class="box-card">
        <el-form 
        :model="queryForm" 
        ref="formRef"
        label-width="80px" 
        style="width: 300px" 
        :rules="rules">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="queryForm.username" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="原密码" prop="oldPassword">
                <el-input v-model="queryForm.oldPassword" placeholder="请输入原密码"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword">
                <el-input v-model="queryForm.newPassword" placeholder="请输入新密码"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
                <el-input v-model="queryForm.confirmPassword" placeholder="请再次输入新密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="modifyPassword">修改密码</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup>
import { ref } from 'vue'
import axios from '@/utils/axios.js'
import { ElMessage } from 'element-plus';
const queryForm = ref({
    username: '',
    password: '',
    oldPassword: '',
    newPassword: '',
    confirmPassword: ''
})
const initFormData = () => {
    const userInfo = window.sessionStorage.getItem('userInfo')
    queryForm.value = JSON.parse(userInfo)
}
initFormData()
const rules = ref({
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 12, message: '长度在 3 到 12个字符', trigger: 'blur' }
    ],
    oldPassword: [
        { required: true, message: '请输入原密码', trigger: 'blur' },
        { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
    ],
    newPassword: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
    ],
    confirmPassword: [
        { required: true, message: '请再次输入新密码', trigger: 'blur' },
        { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
    ]
})

const formRef = ref(null)
const modifyPassword = () => {
    formRef.value.validate(async (valid) => {
        if(valid){
            if(queryForm.value.oldPassword !== queryForm.value.password){
                console.log('原密码不正确')
                ElMessage.error('原密码不正确')
                return
            }else if(queryForm.value.newPassword !== queryForm.value.confirmPassword){
                console.log('两次密码不一致')
                ElMessage.error('两次密码不一致')
                return
            }else {
                try {
                    let r = await axios.post('/modifyPassword', queryForm.value)
                    let data = r.data
                    if(data.code == 0) {
                        console.log(data.msg)
                        ElMessage.success(data.msg)
                        formRef.value.resetFields()
                    }else {
                        console.log(data.msg)
                        ElMessage.error(data.msg)
                    }
                }catch(err){
                    console.log(err)
                    ElMessage.error('系统运行异常')
                }
            }
        }else {
            console.log('fail')
            ElMessage.error('校验失败')
        }
    })
}
</script>

<style lang="less" scoped>
.box-card {
    height: 99%;
}
</style>