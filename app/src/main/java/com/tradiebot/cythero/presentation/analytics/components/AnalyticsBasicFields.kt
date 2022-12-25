package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState

@Composable
fun AnalyticsBasicFields(
    state: AnalyticsScreenState.UserSuccess,
){
    Text(
        textAlign = TextAlign.Center,
        text = "Hello ${state.auth.user.firstName} ${state.auth.user.lastName}",
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .width(240.dp)
            .padding(top = 42.dp, bottom = 20.dp),
    )

    Text(text = "Your username is ${state.auth.user.username}")
    Text(text = "Your email is ${state.auth.user.email}")
    Text(text = "Your id is ${state.auth.user.id}")
    Text(text = "Your sign up date is ${state.auth.user.sign_up_date}")
}