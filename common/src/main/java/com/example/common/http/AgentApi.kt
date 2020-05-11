package com.example.common.http

/**
 *Time:2019/6/14
 *Author:zyh
 *Description:个人觉得写到主app里更合适
 */
object AgentApi {

    fun <T> getApiService(cls: Class<T>, baseUrl: String): T {
        val retrofit = RetrofitUtil.initRetrofit(baseUrl)
        return retrofit.create(cls)
    }
}