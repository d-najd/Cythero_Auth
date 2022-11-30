package com.tradiebot.cythero.presentation.user_info.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserInfoCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(USER_INFO_CARD_PADDING),
    ) {
        Text(
            text = title,
            // fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(USER_INFO_CARD_CONTENT_PADDING)
                .padding(vertical = 16.dp),
        )
        content()
    }
}

val USER_INFO_CARD_CONTENT_PADDING = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
private val USER_INFO_CARD_PADDING = PaddingValues(vertical = 12.dp)
