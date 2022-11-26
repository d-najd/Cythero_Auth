package com.tradiebot.cythero.domain.user.model

import java.io.Serializable

/**
 * @constructor containing all possible possible parameters for a user.
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
) : Serializable