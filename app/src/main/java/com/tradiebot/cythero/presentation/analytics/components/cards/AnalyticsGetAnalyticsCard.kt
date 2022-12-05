package com.tradiebot.cythero.presentation.analytics.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu

@Composable
fun AnalyticsGetAnalyticsCard(
    state: AnalyticsScreenState.Success,
) {

    CytheroCard(
        title = stringResource(R.string.field_analytics),
        modifier = Modifier
            .padding(top = 24.dp)
            .height(200.dp)
    ) {


    }
}

