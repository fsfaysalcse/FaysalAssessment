package com.faysal.assessment.common

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class UserDetailsRoute(val userPostsJson: String)