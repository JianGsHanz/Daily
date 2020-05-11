package com.example.common.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Interceptor.Companion as Interceptor1


/**
 *Time:2019/5/30
 *Author:zyh
 *Description:拦截器
 */
object InterceptorHelper{
    var TAG = "Interceptor"
    /**
     * 日志拦截器
     */
    fun getLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.w(TAG, "LogInterceptor---------: $message")
        }).setLevel(HttpLoggingInterceptor.Level.BODY)//设置打印数据的级别
    }

    /**
     * 重试拦截器
     *
     * @return
     */
    fun getRetryInterceptor(): Interceptor {
        return Interceptor { chain ->
            val maxRetry = 10//最大重试次数
            var retryNum = 5//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

            val request = chain.request()
            var response = chain.proceed(request)
            while (!response.isSuccessful && retryNum < maxRetry) {
                retryNum++
                response = chain.proceed(request)
            }
            response
        }
    }

    /**
     * 请求头拦截器
     *
     * @return
     */
    fun getHeaderInterceptor(): Interceptor = Interceptor { chain ->
            //在这里你可以做一些想做的事,比如token失效时,重新获取token
            //或者添加header等等
            val originalRequest = chain.request()

            if (null == originalRequest.body) {
                return@Interceptor chain.proceed(originalRequest)
            }

            val compressedRequest = originalRequest.newBuilder()
//                .header("Content-Encoding", "gzip")
//                .addHeader("token","77140300327802008a6002b6e43148a19309231afe74a10f")
//                .addHeader("deviceType","android")
//                .addHeader("proId","360sjzs8a518bd623ddbecbb2b2a3bb7f640e30")
//                .addHeader("version","3.3.7")
//                .addHeader("isOpenPush","1")
//                .addHeader("module","wk-front")
                .build()
             return@Interceptor chain!!.proceed(compressedRequest)
    }
}