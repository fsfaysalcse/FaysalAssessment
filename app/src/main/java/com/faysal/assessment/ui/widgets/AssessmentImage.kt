package com.faysal.assessment.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.faysal.assessment.R

@Composable
fun AssessmentImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    alpha: Float = 1f
) {
    val showShimmer = remember { mutableStateOf(true) }


    AsyncImage(
        model = imageUrl,
        contentDescription = "Post Thumbnail",
        modifier = modifier
            .background(
                brush = shimmerBrush(
                    targetValue = 1300f,
                    showShimmer = showShimmer.value
                )
            ),
        onSuccess = { showShimmer.value = false },
        contentScale = contentScale,
        error = painterResource(id = R.drawable.img_placeholder),
        alpha = alpha
    )
}
