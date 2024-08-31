package com.faysal.assessment.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserPosts(
    val user: User,
    val posts: List<Post> = emptyList()
)

val DUMMY_DATA = UserPosts(
    user = User(
        userId = 1,
        albumId = 124,
        name = "Faysal Ahmed",
        url = "https://dummyimage.com/600/92c952&text=User+1",
        thumbnailUrl = "https://dummyimage.com/150/92c952&text=User+1"
    ),
    posts = listOf(
        Post(
            id = 1,
            title = "Post Title",
            body = "Post Body",
            userId = 1
        ),
        Post(
            id = 2,
            title = "Post Title",
            body = "Post Body",
            userId = 1
        )
    )
)

val DUMMY_LIST = listOf(DUMMY_DATA, DUMMY_DATA, DUMMY_DATA, DUMMY_DATA, DUMMY_DATA)