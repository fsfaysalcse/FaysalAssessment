package com.faysal.assessment.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun UsersLoadingShimmerWidget(
    modifier: Modifier = Modifier,
    itemCounts: Int = 10
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(itemCounts) {
            UserLoading(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onUserClick = {}
            )
        }
    }
}

@Composable
fun UserLoading(
    modifier: Modifier = Modifier,
    onUserClick: () -> Unit
) {

    Card(
        modifier = modifier,
        onClick = onUserClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(9.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(
                        brush = shimmerBrush(
                            targetValue = 1300f,
                            showShimmer = true
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(
                            brush = shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = true
                            ),
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(14.dp)
                        .background(
                            brush = shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = true
                            ),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun UserLoadingPreview() {
    UsersLoadingShimmerWidget()
}
