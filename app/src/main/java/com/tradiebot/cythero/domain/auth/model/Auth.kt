package com.tradiebot.cythero.domain.auth.model

import com.tradiebot.cythero.domain.user.model.User
import java.io.Serializable

data class Auth(
    val organization: Long?,
    val refresh: String,
    val token: String,
    val user: User,
) : Serializable