import axios from 'axios'

const baseURL = 'http://localhost:8080'
const httpService = axios.create({
    baseURL: baseURL, // api的base_url
    timeout: 3000 //请求超时时间
})

httpService.interceptors.request.use(function (config) {
    config.headers.token = window.sessionStorage.getItem('token')
    return config
}, function (error) {
    return Promise.reject(error)
})

httpService.interceptors.response.use(function (response) {
    return response
}, function (error) {
    return Promise.reject(error)
})

export function get(url, params = {}) {
    return new Promise((resolve, reject) => {
        httpService({
            url,
            method: 'get',
            params
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}

export function post(url, data = {}) {
    return new Promise((resolve, reject) => {
        httpService({
            url,
            method: 'post',
            data
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}

export function fileUpload(url, data = {}) {
    return new Promise((resolve, reject) => {
        httpService({
            url,
            method: 'post',
            data,
            headers: {'Content-Type': 'multipart/form-data'}
        }).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}

export function getServiceUrl() {
    return baseURL
}

export default {
    get,
    post,
    fileUpload,
    getServiceUrl
}