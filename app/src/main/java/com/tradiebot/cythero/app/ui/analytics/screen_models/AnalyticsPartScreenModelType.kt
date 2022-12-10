package com.tradiebot.cythero.app.ui.analytics.screen_models

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.app.ui.analytics.AnalyticsType
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.interactor.RequestPartAnalytics
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.logcat
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AnalyticsPartScreenModelType(
    val context: Context,
    val auth: Auth,
    private val requestPartAnalytics: RequestPartAnalytics = Injekt.get(),
) : StateScreenModel<AnalyticsPartScreenState>(AnalyticsPartScreenState.AwaitingSelection),
    AnalyticsScreenModelType {

    override fun getReportType(): AnalyticsType = AnalyticsType.PART

    override fun requestedDifferentAnalytics() {
        coroutineScope.launch {
            mutableState.update {
                AnalyticsPartScreenState.AwaitingSelection
            }
        }
    }

    fun requestAnalytics(auth: Auth, userID: Long = auth.user.id!!, part: Part){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsPartScreenState.Loading }
            val partAnalytics = requestPartAnalytics.await(auth, userID, part)
            if(partAnalytics != null) {
                mutableState.update {
                    AnalyticsPartScreenState.Success(
                        auth = auth,
                        analytics = listOf(partAnalytics)
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update { AnalyticsPartScreenState.AwaitingSelection }
            }
        }
    }

    fun requestAnalytics(auth: Auth, userIDs: List<Long>, parts: List<Part>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsPartScreenState.Loading }
            val partAnalytics = requestPartAnalytics.await(auth, userIDs, parts)
            if(partAnalytics.isNotEmpty()) {
                mutableState.update {
                    AnalyticsPartScreenState.Success(
                        auth = auth,
                        analytics = partAnalytics
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update { AnalyticsPartScreenState.AwaitingSelection }
            }
        }
    }
}

sealed class AnalyticsPartScreenState {

    @Immutable
    object AwaitingSelection : AnalyticsPartScreenState()

    @Immutable
    object Loading: AnalyticsPartScreenState()

    @Immutable
    data class Success(
        val auth: Auth,
        val analytics: List<AnalyticsPart>,
    ) : AnalyticsPartScreenState()
}
