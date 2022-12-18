package com.tradiebot.cythero.network.utils

@Suppress("unused", "MemberVisibilityCanBePrivate")
object Urls {
    //region main
    const val WEBSITE: String = "https://api.cythero.com"
    const val API_RAW: String = "/api"
    const val API: String = WEBSITE + API_RAW
    //endregion

    //region user
    const val USER_RAW = "/user"
    const val USER = API + USER_RAW
    //endregion

    //region auth
    const val AUTH_RAW = "/auth"
    const val AUTH = USER + AUTH_RAW

    const val AUTH_REGISTER = "$AUTH/register"
    const val AUTH_LOGIN = "$AUTH/login"
    const val AUTH_REFRESH_USER_SESSION = "$AUTH/refresh"
    //endregion

    //region Analytics
    const val ANALYTICS_RAW = "/analytics"
    const val ANALYTICS = API + ANALYTICS_RAW
    
    const val ANALYTICS_LABELS = "$ANALYTICS/anal_labels?hashed=true"
    const val ANALYTICS_ANALYTICS = ANALYTICS + ANALYTICS_RAW
    
    const val ANALYTICS_USER_SPRAYVERSE = "$ANALYTICS/sprayverse_user_report"
    const val ANALYTICS_PART_SPRAYVERSE = "$ANALYTICS/sprayverse_part_report"
    const val ANALYTICS_USAGE = "$ANALYTICS/usage_report"
    
}