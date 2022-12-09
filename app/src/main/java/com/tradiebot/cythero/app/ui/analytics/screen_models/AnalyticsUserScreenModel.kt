package com.tradiebot.cythero.app.ui.analytics.screen_models

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.user.interactor.RequestUserAnalytics
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.logcat
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.Date

/**
 * NOTE lazy injection can be used for performance
 */
class AnalyticsUserScreenModel(
    val context: Context,
    val auth: Auth,
    private val requestUserAnalytics: RequestUserAnalytics = Injekt.get()
) : StateScreenModel<AnalyticsUserReportScreenState>(AnalyticsUserReportScreenState.AwaitingSelection),
    RequestedDifferentAnalytics {

    /** requesting analytics for single user and updates the state */
    fun requestAnalytics(auth: Auth, userID: Long = auth.user.id!!, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsUserReportScreenState.Loading }
            val userAnalytics = requestUserAnalytics.await(auth, userID, dateRange)
            if(userAnalytics != null) {
                mutableState.update {
                    AnalyticsUserReportScreenState.Success(
                        auth = auth,
                        // FIXME passing the user id here is not correct
                        analyticsUser = mapOf(auth.user.id!! to userAnalytics),
                    )
                }
            } else {
                logcat { "Something went wrong" }
            }
        }
    }

    /** requesting analytics for multiple users and updates the state */
    @Suppress("unused")
    fun requestAnalytics(auth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsUserReportScreenState.Loading }
            val userAnalytics = requestUserAnalytics.await(auth, userIDs, dateRange)
            if(userAnalytics.isNotEmpty()) {
                mutableState.update {
                    AnalyticsUserReportScreenState.Success(
                        auth = auth,
                        analyticsUser = userAnalytics,
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
        val analyticsUser: Map<Long, AnalyticsUser>,
    ) : AnalyticsUserReportScreenState()
}
