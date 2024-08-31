package com.faysal.assessment.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faysal.assessment.data.models.User
import com.faysal.assessment.ui.theme.Nunito

@Composable
fun UserCardWidget(
    modifier: Modifier = Modifier,
    user: User,
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
            AssessmentImage(
                imageUrl = user.thumbnailUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = Nunito,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Post Count ${user.postCount}",
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = Nunito,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }

}

@Preview
@Composable
fun PostCardWidgetPreview() {
    UserCardWidget(
        user = User(
            userId = 1,
            albumId = 124,
            name = "Faysal Ahmed",
            url = "https://dummyimage.com/600/92c952&text=User+1",
            thumbnailUrl = "https://dummyimage.com/150/92c952&text=User+1"
        ),
        modifier = Modifier.fillMaxWidth().height(120.dp),
    ) {

    }
}