package com.tradiebot.cythero.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.login.LoginScreenState
import com.tradiebot.cythero.domain.user.model.UserSign

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ColumnScope.LoginCardContent(
    state: LoginScreenState.Success,
    contentPadding: PaddingValues,
    onClickUserLogin: (UserSign) -> Unit,
    onClickRegister: (UserSign) -> Unit,
) {
    Image(
        painter = painterResource(R.drawable.company_logo),
        contentDescription = stringResource(R.string.company_logo),
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(12.dp),
    )

    Text(
        textAlign = TextAlign.Center,
        text = stringResource(R.string.info_sign_in_to_access),
        fontSize = TextUnit(16F, TextUnitType.Sp),
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .width(200.dp)
            .align(Alignment.CenterHorizontally)
            .padding(top = 12.dp),
    )

    LoginFields(
        state = state,
        onClickUserLogin = onClickUserLogin,
        onClickRegister = onClickRegister,
    )
}