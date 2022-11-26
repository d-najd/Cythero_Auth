package com.tradiebot.cythero.network.utils

class Urls {
    @Suppress("unused", "MemberVisibilityCanBePrivate")
    companion object {
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
    }
}