package com.faysal.assessment.common

import com.faysal.assessment.data.models.UserPosts
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class UserDetailsRoute(val posts: UserPosts)