package com.tradiebot.cythero.presentation.register.components
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.width
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
import com.tradiebot.cythero.app.ui.register.RegisterScreenState
import com.tradiebot.cythero.domain.user.model.UserRegister
import com.tradiebot.cythero.presentation.components.CytheroLogo

@Composable
fun ColumnScope.RegisterCardContent(
    state: RegisterScreenState.Success,
    onClickUserRegister: (UserRegister) -> Unit,
    onClickLogin: () -> Unit,

    onMissingFields: () -> Unit,
    onNotMatchingPassword: () -> Unit,
) {
    
    CytheroLogo()
    
    Text(
        textAlign = TextAlign.Center,
        text = stringResource(R.string.info_sign_up_to_access),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White.copy(.8f),
        modifier = Modifier
            .width(200.dp)
            .align(Alignment.CenterHorizontally),
    )

    RegisterFields(
        state = state,
        onClickUserRegister = onClickUserRegister,
        onClickLogin = onClickLogin,
        onMissingFields = onMissingFields,
        onNotMatchingPassword = onNotMatchingPassword,
    )
}
