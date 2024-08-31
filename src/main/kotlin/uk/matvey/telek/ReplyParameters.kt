package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [ReplyParameters](https://core.telegram.org/bots/api#replyparameters)
 */
@Serializable
data class ReplyParameters(
    @SerialName("message_id")
    val messageId: Int,
)
