package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [Message](https://core.telegram.org/bots/api#message)
 */
@Serializable
data class Message(
    @SerialName("message_id")
    val id: Int,
)
