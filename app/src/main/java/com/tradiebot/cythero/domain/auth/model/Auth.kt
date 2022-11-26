package com.tradiebot.cythero.domain.auth.model

import com.tradiebot.cythero.domain.user.model.UserComplete
import java.io.Serializable

data class Auth(
    val organization: Long?,
    val refresh: String,
    val token: String,
    val user: UserComplete,
) : Serializable