package com.tradiebot.cythero.presentation.register.components

import android.graphics.BitmapFactory
import android.renderscript.Allocation
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.renderscript.Toolkit
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.register.RegisterScreenState
import com.tradiebot.cythero.domain.user.model.UserRegister

@Composable
fun RegisterContent(
    state: RegisterScreenState.Success,
    contentPadding: PaddingValues,
    onClickUserRegister: (UserRegister) -> Unit,
    onClickLogin: () -> Unit,

    onMissingFields: () -> Unit,
    onNotMatchingPassword: () -> Unit,
) {

    val bitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources,
        R.drawable.cythero_banner
    ).let { Toolkit.blur(it, 25) }

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = stringResource(R.string.company_banner),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize(),
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = .1f)),
            border = BorderStroke(1.dp, Color.White.copy(alpha = .4f)),
            modifier = Modifier
                .padding(12.dp)
                .background(Color.Transparent)
                .verticalScroll(rememberScrollState()),
        ) {
            RegisterCardContent(
                state = state,
                onClickUserRegister = onClickUserRegister,
                onClickLogin = onClickLogin,
                onMissingFields = onMissingFields,
                onNotMatchingPassword = onNotMatchingPassword,
            )
        }
    }
}