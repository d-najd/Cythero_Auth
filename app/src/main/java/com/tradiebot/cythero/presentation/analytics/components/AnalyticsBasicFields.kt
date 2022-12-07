package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AnalyticsBasicFields(
    state: AnalyticsUserReportScreenState.Success,
){
    Text(
        textAlign = TextAlign.Center,
        text = "Hello ${state.auth.user.firstName} ${state.auth.user.lastName}",
        fontSize = TextUnit(24F, TextUnitType.Sp),
        lineHeight = TextUnit(32F, TextUnitType.Sp),
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