package com.tradiebot.cythero.presentation.analytics

import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    state: AnalyticsScreenState,
    onBackClicked: () -> Unit
) {
    Scaffold { contentPadding ->
        BackHandler{ onBackClicked() }

        AnalyticsContent(
            state = state,
            contentPadding = contentPadding,
        )
    }
}
