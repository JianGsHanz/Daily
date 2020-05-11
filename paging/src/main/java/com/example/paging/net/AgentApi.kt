package com.example.paging.net

import com.example.common.http.RetrofitUtil

/**
 *Time:2019/6/14
 *Author:zyh
 *Description:
 */
object AgentApi {

    fun getApiService(): ApiService {
        val retrofit = RetrofitUtil.initRetrofit(ApiService.BASE_URL)
        return retrofit.create(ApiService::class.java)
    }

    fun getOtherApiService(): ApiService{
        val retrofit = RetrofitUtil.initRetrofit(ApiService.BASE_OTHER_URL)
        return retrofit.create(ApiService::class.java)
    }
}