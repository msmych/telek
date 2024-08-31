package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [ChatBoostRemoved](https://core.telegram.org/bots/api#chatboostremoved)
 */
@Serializable
data class ChatBoostRemoved(
    val chat: Chat,
)
