<template>
    <div class="login-container">
        <div class="login-box">
            <el-form 
                :model="loginForm" 
                ref="formRef"
                :rules="rules">
                <div class="title-container">
                    <h3 class="title">ILEEMail管理登录</h3>
                </div>
                <el-form-item prop="username" class="input-item">
                    <el-icon size="20px">
                        <User/>
                    </el-icon>
                    <el-input class="inputDeep" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item  prop="password" class="input-item">
                    <el-icon size="20px">
                        <Lock/>
                    </el-icon>
                    <el-input class="inputDeep" :show-password="true" type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" class="login-button" @click="handleLogin">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue'
    import { User, Lock } from '@element-plus/icons-vue'
    import axios from 'axios'
    import { ElMessage } from 'element-plus'
    import axiosUtil from '@/utils/axios'
    import router from '@/router'
    const loginForm = ref({
        username: '',
        password: ''
    })
    
    const rules = ref({
        username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 12, message: '长度在 3 到 12个字符', trigger: 'blur' }
        ],
        password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
        ]
    })

    const formRef = ref(null)
    const handleLogin = () => {
        formRef.value.validate(async (valid) => {
            if (valid) {
                try {
                    let r = await axiosUtil.post('/adminLogin', loginForm.value)
                    let {data} = r
                    if(data.code == 0) {
                        ElMessage.success('登录成功')
                        window.sessionStorage.setItem('token', data.token)
                        window.sessionStorage.setItem('userInfo', JSON.stringify(loginForm.value))
                        router.replace('/')
                    }else {
                        ElMessage.error(data.msg)
                    }
                } catch (error) {
                    console.log('err:' + error)
                    ElMessage.error('服务器故障,联系管理员')
                }
            } else {
                console.log('校验失败')
                ElMessage.error('校验失败')
            }
        })
    }    
</script>

<style lang="less" scoped>
    .login-container {
        width: 100%;
        height: 100vh;
        background-color: #f5f5f5;
        display: flex;
        justify-content: center;
        align-items: center;
        .login-box {
            display: flex;
            flex-direction: column;
            justify-content: center;
            width: 400px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, .1);
            padding: 20px;
            .title-container {
                text-align: center;
                margin-bottom: 20px;
                .title {
                    font-size: 20px;
                    font-weight: bold;
                }
            }
            .input-item {
                :deep(.el-form-item__content) {
                    flex-wrap: nowrap;
                }

                :deep(.el-form-item__error) {
                    left: 32px;
                }
                .inputDeep {
                    :deep(.el-input__wrapper) {
                      box-shadow: 0 0 0 0px var(--el-input-border-color, var(--el-border-color)) inset;
                      cursor: text;
                      .el-input__inner {
                        cursor: text !important;
                      }
                    }
                }
            }
            .login-button {
                width: 100%;
            }
        }
    }
    

    

    

    
</style>