package com.faysal.assessment.data.models

data class User(
    val albumId: Int,
    val name: String,
    val thumbnailUrl: String,
    val url: String,
    val userId: Int,
    val postCount: Int = 0
)