package com.tradiebot.cythero.app.ui.analytics

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.interactor.RequestAnalytics
import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import logcat.logcat
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AnalyticsScreenModel(
    val context: Context,
    val auth: Auth,
    private val requestAnalytics: RequestAnalytics = Injekt.get(),
) : StateScreenModel<AnalyticsScreenState>(AnalyticsScreenState.Loading) {

    init {
        coroutineScope.launchIO {
            val userAnalytics = requestAnalytics.await(auth, 4L)
            if(userAnalytics != null) {
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth,
                        //FIXME passing the user id here is not correct
                        userAnalytics = mapOf(auth.user.id!! to userAnalytics),
                    )
                }
            } else {
                logcat { "Something went wrong" }
            }
        }
    }

}

sealed class AnalyticsScreenState {
    @Immutable
    object Loading : AnalyticsScreenState()

    @Immutable
    data class Success(
        val auth: Auth,
        val userAnalytics: Map<Long, Analytics>,
    ) : AnalyticsScreenState()
}