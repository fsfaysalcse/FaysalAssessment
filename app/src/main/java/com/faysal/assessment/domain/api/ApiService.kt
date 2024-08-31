package com.faysal.assessment.domain.api

import com.faysal.assessment.common.Resource
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("SharminSirajudeen/test_resources/posts")
    suspend fun getUserPosts(): Response<ResponseBody>

    @GET("SharminSirajudeen/test_resources/users")
    suspend fun getUsers(): Response<ResponseBody>
}