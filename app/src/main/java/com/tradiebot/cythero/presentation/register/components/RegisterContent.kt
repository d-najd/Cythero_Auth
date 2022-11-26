@file:OptIn(ExperimentalUnitApi::class)

package com.tradiebot.cythero.presentation.register.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.register.RegisterScreenState

@OptIn(ExperimentalUnitApi::class)
@Composable
fun RegisterContent(
    state: RegisterScreenState.Success,
    contentPadding: PaddingValues,
) {
    Image(
        painter = painterResource(R.drawable.cythero_banner),
        contentDescription = stringResource(R.string.company_banner),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .blur(radius = 12.dp) // blur doesn't want to work
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
                contentPadding = contentPadding
            )
        }
    }
}