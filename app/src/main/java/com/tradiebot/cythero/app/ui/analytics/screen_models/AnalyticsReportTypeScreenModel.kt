package com.tradiebot.cythero.app.ui.analytics.screen_models

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.auth.model.Auth
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnalyticsReportTypeScreenModel(
    val context: Context,
    val auth: Auth,
) : StateScreenModel<AnalyticsReportTypeScreenState>(AnalyticsReportTypeScreenState.Loading) {

    init {
        coroutineScope.launch {
            mutableState.update {
                AnalyticsReportTypeScreenState.Success(
                    auth = auth
                )
            }
        }
    }
}

sealed class AnalyticsReportTypeScreenState {

    @Immutable
    object Loading : AnalyticsReportTypeScreenState()

    @Immutable
    data class Success(
        val auth: Auth,
    ) : AnalyticsReportTypeScreenState()
}
