package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [MessageReactionUpdated](https://core.telegram.org/bots/api#messagereactionupdated)
 */
@Serializable
data class MessageReactionUpdated(
    val chat: Chat,
    @SerialName("message_id")
    val messageId: Int,
)
