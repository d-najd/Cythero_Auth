package com.tradiebot.cythero.presentation.user_info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserInfoGeneralCard(){
    val cardContentPadding = USER_INFO_CARD_CONTENT_PADDING

    /**
     * TODO move strings to string resources
     */
    UserInfoCard(
        title = "General",
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "User:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = "Konstantin",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "Times Played:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = "7",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "Most Painted Part:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = "Fender",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "Total Time Painted:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = "19 min.",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardContentPadding)
                .padding(top = 8.dp),
        ) {
            Text(text = "Export to PDF")
        }
    }
}