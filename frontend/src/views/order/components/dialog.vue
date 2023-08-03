<template>
    <el-dialog 
        model-value="dialogTableVisible" 
        :show-close="false" 
        title="商品订单详情"
        width="40%">
        <template #header="{ titleId, titleClass }">
            <div class="my-header">
            <h4 :id="titleId" :class="titleClass">商品订单详情</h4>
            <el-button link type="danger" @click="close">
                <el-icon :size="25" class="el-icon--left"><CircleCloseFilled /></el-icon>
            </el-button>
            </div>
        </template>
        <el-table :data="orderDetail" style="width:100%">
        <el-table-column type="index" label="" width="50" />
        <el-table-column property="goodsPic" label="商品图片" width="150">
            <template #default="{ row }">
            <img :src="getServiceUrl() + '/image/hotImage/' + row.goodsPic" alt="" style="width: 80px; height: 80px" />
            </template>
        </el-table-column>
        <el-table-column property="goodsName" label="商品名称" width="200" />
        <el-table-column property="goodsPrice" label="商品价格" width="100" />
        <el-table-column property="goodsNumber" label="商品数量" width="80" />
        </el-table>
    </el-dialog>
</template>

<script setup>
import { ref, defineEmits, defineProps, watch } from 'vue'
import { CircleCloseFilled } from '@element-plus/icons-vue'
import axios, { getServiceUrl } from '@/utils/axios.js'

const emits = defineEmits(['update:modelValue'])
const props = defineProps({
    id: {
        type: Number,
        default: -1,
        required: true
    }
})
const orderDetail = ref([])
watch(
    () => props.id,
    () => {
        initOrderDetailData(props.id)
    }
)
const initOrderDetailData = async (id) => {
    const r = await axios.get('/admin/orderDetail/listByOrderId/' + id)
    orderDetail.value = r.data.orderDetailList
}
const close = () => {
    emits('update:modelValue', false)
}
</script>

<style lang="less" scoped>
.my-header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
</style>