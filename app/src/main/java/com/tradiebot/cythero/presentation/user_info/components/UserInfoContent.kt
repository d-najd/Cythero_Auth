package com.tradiebot.cythero.presentation.user_info.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.user_info.UserInfoScreenState
import com.tradiebot.cythero.presentation.components.PieChartComponent

@Composable
fun UserInfoContent(
    state: UserInfoScreenState.Success,
    contentPadding: PaddingValues,
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxWidth()
    ) {

        PieChartComponent()


        Divider(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 12.dp)
        )

        UserInfoBasicFields(
            state = state,
        )
    }
}