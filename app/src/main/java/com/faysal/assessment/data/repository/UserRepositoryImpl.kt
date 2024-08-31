package com.faysal.assessment.data.repository

import com.faysal.assessment.common.Resource
import com.faysal.assessment.data.models.PostResponse
import com.faysal.assessment.data.models.UserResponse
import com.faysal.assessment.domain.api.ApiService
import com.faysal.assessment.domain.repository.UserRepository
import com.google.gson.Gson
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson
) : UserRepository {

    override suspend fun getUserPosts(): Resource<PostResponse> = handleApiResponse {
        val response = apiService.getUserPosts()
        gson.fromJson(response.body()?.string(), PostResponse::class.java)
    }

    override suspend fun getUsers(): Resource<UserResponse> = handleApiResponse {
        val response = apiService.getUsers()
        gson.fromJson(response.body()?.string(), UserResponse::class.java)
    }

    private inline fun <T> handleApiResponse(apiCall: () -> T?): Resource<T> {
        return try {
            val result = apiCall()
            if (result != null) {
                Resource.Success(result)
            } else {
                Resource.Error("Error: Empty response body", null)
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}", null)
        }
    }
}