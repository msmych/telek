package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [MessageReactionCountUpdated](https://core.telegram.org/bots/api#messagereactioncountupdated)
 */
@Serializable
data class MessageReactionCountUpdated(
    val chat: Chat,
    @SerialName("message_id")
    val messageId: Int,
    val date: Int,
    val reactions: List<ReactionCount>,
)
