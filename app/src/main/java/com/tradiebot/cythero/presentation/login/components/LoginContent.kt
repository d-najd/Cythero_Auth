@file:OptIn(ExperimentalUnitApi::class)

package com.tradiebot.cythero.presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.login.LoginScreenState
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.presentation.components.Divider
import com.tradiebot.cythero.presentation.login.components.fields.LoginFields

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LoginContent(
    state: LoginScreenState, //.Success
    contentPadding: PaddingValues,
    onLoginUser: (UserLogin) -> Unit,
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxWidth()
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Continue Where You Left Off",
            fontSize = TextUnit(24F, TextUnitType.Sp),
            lineHeight = TextUnit(32F, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .width(240.dp)
                .padding(top = 42.dp),
        )

        Divider(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            color = Color.White
        )

        LoginFields(
            state = state,
            onLoginUser = onLoginUser)
    }
}