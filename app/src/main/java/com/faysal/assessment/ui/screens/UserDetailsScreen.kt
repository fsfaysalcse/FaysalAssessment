package com.faysal.assessment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.faysal.assessment.R
import com.faysal.assessment.data.models.DUMMY_DATA
import com.faysal.assessment.data.models.UserPosts
import com.faysal.assessment.ui.widgets.AssessmentImage
import com.faysal.assessment.ui.widgets.PostWidget

@Composable
fun UserDetailsScreen(
    navController: NavHostController? = null,
    userPosts: UserPosts
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AssessmentImage(
                    imageUrl = userPosts.user.thumbnailUrl,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                IconButton(
                    onClick = {
                        navController?.navigateUp()
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                        .size(45.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }


            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                userPosts.posts.forEach { post ->
                    PostWidget(
                        post = post,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }

}

@Preview
@Composable
fun UserDetailsScreenPreview() {
    UserDetailsScreen(userPosts = DUMMY_DATA, navController = null)
}