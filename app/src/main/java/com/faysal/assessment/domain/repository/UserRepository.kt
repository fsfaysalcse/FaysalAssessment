package com.faysal.assessment.domain.repository

import com.faysal.assessment.common.Resource
import com.faysal.assessment.data.models.PostResponse
import com.faysal.assessment.data.models.UserResponse

interface UserRepository {
    suspend fun getUserPosts(): Resource<PostResponse>
    suspend fun getUsers(): Resource<UserResponse>
}