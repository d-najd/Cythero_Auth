@file:OptIn(ExperimentalUnitApi::class)

package com.tradiebot.cythero.presentation.user_info.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.user_info.UserInfoScreenState

@OptIn(ExperimentalUnitApi::class)
@Composable
fun UserInfoContent(
    state: UserInfoScreenState.Success, //.Success
    contentPadding: PaddingValues,
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxWidth()
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Hello ${state.user?.firstName} ${state.user?.lastName}",
            fontSize = TextUnit(24F, TextUnitType.Sp),
            lineHeight = TextUnit(32F, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .width(240.dp)
                .padding(top = 42.dp, bottom = 20.dp),
        )

        Text(text = "Your username is ${state.user?.username}")
        Text(text = "Your email is ${state.user?.email}")
        Text(text = "Your id is ${state.user?.id}")
        Text(text = "Your sign up date is ${state.user?.sign_up_date}")
    }
}