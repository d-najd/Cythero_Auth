package com.tradiebot.cythero.presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.login.LoginScreenState
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.presentation.components.CytheroLogo

@Composable
fun ColumnScope.LoginCardContent(
    state: LoginScreenState.Success,
    onClickUserLogin: (UserLogin) -> Unit,
    onClickRegister: () -> Unit,

    onMissingFields: () -> Unit,
) {
    
    CytheroLogo()

    Text(
        textAlign = TextAlign.Center,
        text = stringResource(R.string.info_sign_in_to_access),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White.copy(.8f),
        modifier = Modifier
            .width(200.dp)
            .align(Alignment.CenterHorizontally),
    )

    LoginFields(
        state = state,
        onClickUserLogin = onClickUserLogin,
        onClickRegister = onClickRegister,
        onMissingFields = onMissingFields,
    )
}