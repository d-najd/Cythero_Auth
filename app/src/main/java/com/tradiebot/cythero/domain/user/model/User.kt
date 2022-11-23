package com.tradiebot.cythero.domain.user.model

import java.io.Serializable
import java.util.Date


@Deprecated("Use UserLoginUpdate")
data class UserLogin(
    val email: String?,
    val username: String?,
    val password: String,
    val device_number: String?,
    val device_nickname: String?,
    val pin: Int?,
    val from_web: Boolean = true,
) : Serializable

/**
 * @constructor constructor containing all possible possible parameters, use this when you are
 * not sure what to use
 * @property sign_up_date should be possible to be replaced with 'Date'
 */
data class UserComplete(
    val id: Long,
    val archived: Long?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val organization_id: Long?,
    val sign_up_date: String?,
    val type_id: Long?,
    val username: String?,
    val password: String?,
    val pin: Int?,
    val device_number: String?,
    val device_nickname: String?,
    val from_web: Boolean?,
    val application_id:Long?,
) : Serializable {
    //Possibly override hashcode to only use id as an optimization?
}