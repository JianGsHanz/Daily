package com.example.paging.net

import com.example.common.api.ApiResult
import com.example.paging.bean.GithubAccount
import retrofit2.http.*


interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
        const val BASE_OTHER_URL = "https://api.github.com"
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


    @GET("users")
    suspend fun getGithubAccount(@Query("since") id: Long, @Query("per_page") perPage: Int):
            List<GithubAccount>
}