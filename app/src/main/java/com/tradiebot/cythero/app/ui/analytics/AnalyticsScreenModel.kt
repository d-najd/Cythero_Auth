package com.tradiebot.cythero.app.ui.analytics

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.interactor.RequestPartAnalytics
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.usage.interactor.RequestUsageAnalytics
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortable
import com.tradiebot.cythero.domain.analytics.usage.model.toAnalyticsSortable
import com.tradiebot.cythero.domain.analytics.user.interactor.RequestUserAnalytics
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.logcat
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.*

class AnalyticsScreenModel(
    val context: Context,
    val auth: Auth,
    private val requestUserAnalytics: RequestUserAnalytics = Injekt.get(),
    private val requestPartAnalytics: RequestPartAnalytics = Injekt.get(),
    private val requestUsageAnalytics: RequestUsageAnalytics = Injekt.get(),
) : StateScreenModel<AnalyticsScreenState>(AnalyticsScreenState.Loading) {

    init {
        coroutineScope.launch {
            mutableState.update {
                AnalyticsScreenState.Success(
                    auth = auth
                )
            }
        }
    }

    // region user

    /** requesting analytics for single user and updates the state */
    fun requestUserAnalytics(auth: Auth, userID: Long = auth.user.id!!, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val userAnalytics = requestUserAnalytics.await(auth, userID, dateRange)
            if(userAnalytics != null) {
                mutableState.update {
                    AnalyticsScreenState.UserSuccess(
                        auth = auth,
                        analytics = mapOf(auth.user.id!! to userAnalytics)
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth
                    )
                }
            }
        }
    }

    /** requesting analytics for multiple users and updates the state */
    @Suppress("unused")
    fun requestUserAnalytics(auth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val userAnalytics = requestUserAnalytics.await(auth, userIDs, dateRange)
            if(userAnalytics.isNotEmpty()) {
                mutableState.update {
                    AnalyticsScreenState.UserSuccess(
                        auth = auth,
                        analytics = userAnalytics,
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth
                    )
                }
            }
        }
    }

    //endregion

    //region part

    fun requestPartAnalytics(auth: Auth, userID: Long = auth.user.id!!, part: Part) {
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val partAnalytics = requestPartAnalytics.await(auth, userID, part)
            if (partAnalytics != null) {
                val temp = listOf(partAnalytics) // TODO fix crash here when large object's mutable state tries to be updated
                mutableState.update {
                    AnalyticsScreenState.PartSuccess(
                        auth = auth,
                        analytics = temp
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth
                    )
                }
            }
        }
    }

    @Suppress("unused")
    fun requestPartAnalytics(auth: Auth, userIDs: List<Long>, parts: List<Part>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.Loading }
            val partAnalytics = requestPartAnalytics.await(auth, userIDs, parts)
            if(partAnalytics.isNotEmpty()) {
                mutableState.update {
                    AnalyticsScreenState.PartSuccess(
                        auth = auth,
                        analytics = partAnalytics,
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth,
                    )
                }
            }
        }
    }

    //endregion
    
    //region usage
    
    /** requesting analytics for single user and updates the state */
    fun requestUsageAnalytics(auth: Auth, userID: Long = auth.user.id!!, dateRange: Pair<Date, Date>){
        coroutineScope.launchIO {
            mutableState.update { AnalyticsScreenState.LoadingType }
            val userAnalytics = requestUsageAnalytics.await(auth, userID, dateRange)
            if(userAnalytics != null) {
                mutableState.update {
                    AnalyticsScreenState.UsageSuccess(
                        auth = auth,
                        analytics = userAnalytics.toAnalyticsSortable(),
                    )
                }
            } else {
                logcat { "Something went wrong" }
                mutableState.update {
                    AnalyticsScreenState.Success(
                        auth = auth,
                    )
                }
            }
        }
    }
    
    //endregion

}

sealed class AnalyticsScreenState {

    /** if the screen is loading in */
    @Immutable
    object Loading : AnalyticsScreenState()

    /** if the screen has successfully loaded but no screen has been selected */
    @Immutable
    data class Success(
        val auth: Auth,
    ) : AnalyticsScreenState()

    /** if a report type is being processed but the screen has loaded */
    @Immutable
    object LoadingType : AnalyticsScreenState()

    /** if user analytics were success */
    @Immutable
    data class UserSuccess(
        val auth: Auth,
        val analytics: Map<Long, AnalyticsUser>,
    ) : AnalyticsScreenState()


    /** if part analytics were success */
    @Immutable
    data class PartSuccess(
        val auth: Auth,
        val analytics: List<AnalyticsPart>,
    ) : AnalyticsScreenState()
    
    
    /** if usage analytics were success */
    @Immutable
    data class UsageSuccess(
        val auth: Auth,
        val analytics: List<AnalyticsUsageSortable>,
    ) : AnalyticsScreenState()
    
}
