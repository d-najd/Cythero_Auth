package com.tradiebot.cythero.app.ui.analytics.screen_models

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.interactor.RequestAnalytics
import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.logcat
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

/**
 * NOTE lazy injection can be used for performance
 */
class AnalyticsUserTypeScreenModel(
    val context: Context,
    val auth: Auth,
    private val requestAnalytics: RequestAnalytics = Injekt.get()
    // private val requestAnalytics: RequestAnalytics = Injekt.get(),
) : StateScreenModel<AnalyticsUserReportScreenState>(AnalyticsUserReportScreenState.AwaitingSelection),
    RequestedDifferentAnalytics {

    /** requesting analytics for single user and updates the state */
    fun requestAnalytics(auth: Auth, userID: Long = auth.user.id!!){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsUserReportScreenState.Loading }
            val userAnalytics = requestAnalytics.await(auth, userID)
            if(userAnalytics != null) {
                mutableState.update {
                    AnalyticsUserReportScreenState.Success(
                        auth = auth,
                        // FIXME passing the user id here is not correct
                        userAnalytics = mapOf(auth.user.id!! to userAnalytics),
                    )
                }
            } else {
                logcat { "Something went wrong" }
            }
        }
    }

    /** requesting analytics for multiple users and updates the state */
    fun requestAnalytics(auth: Auth, userIDs: List<Long>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsUserReportScreenState.Loading }
            val userAnalytics = requestAnalytics.await(auth, userIDs)
            if(userAnalytics.isNotEmpty()) {
                mutableState.update {
                    AnalyticsUserReportScreenState.Success(
                        auth = auth,
                        userAnalytics = userAnalytics,
                    )
                }
            } else {
                logcat { "Something went wrong" }
            }
        }
    }

    override fun requestedDifferentAnalytics() {
        coroutineScope.launch {
            mutableState.update {
                AnalyticsUserReportScreenState.AwaitingSelection
            }
        }
    }
}

sealed class AnalyticsUserReportScreenState {

    @Immutable
    object AwaitingSelection : AnalyticsUserReportScreenState()

    @Immutable
    object Loading: AnalyticsUserReportScreenState()

    @Immutable
    data class Success(
        val auth: Auth,
        val userAnalytics: Map<Long, Analytics>,
    ) : AnalyticsUserReportScreenState()
}
