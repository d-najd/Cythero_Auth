package com.tradiebot.cythero.domain.user.model

import java.io.Serializable


/**
 * data class containing all possible possible parameters for a user.
 * @property sign_up_date should be possible to be replaced with 'Date'
 * @see UserLogin constructor used in requests for logging in users
 * @see UserRegister constructor used in requests for registering users
 */
data class User(
    val id: Long? = null,
    val archived: Long? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val organization_id: Long? = null,
    val sign_up_date: String? = null,
    val type_id: Long? = null,
    val username: String? = null,
    val password: String? = null,
    val pin: Int? = null,
    val device_number: String? = null,
    val device_nickname: String? = null,
    val from_web: Boolean? = null,
    val application_id: Long? = null,
): Serializable {
    companion object {
        fun mockInstance() = User(
            email = "dimitar.najdovski.example@gmail.com",
            password = "Dimitar123",
        )
    }
}