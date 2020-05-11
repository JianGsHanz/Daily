package com.example.common.http

import com.example.common.api.ApiResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * 个人觉得写到主app里更合适
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }


    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): ApiResult<Any?>

    @POST("lg/uncollect_originId/{id}/json")
    suspend fun uncollect(@Path("id") id: Int): ApiResult<Any?>

    @FormUrlEncoded
    @POST("lg/user_article/add/json")
    suspend fun shareArticle(
        @Field("title") title: String,
        @Field("link") link: String
    ): ApiResult<Any>


    @POST("lg/user_article/delete/{id}/json")
    suspend fun deleteShare(@Path("id") id: Int): ApiResult<Any>
}