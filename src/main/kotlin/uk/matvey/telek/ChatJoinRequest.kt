package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [ChatJoinRequest](https://core.telegram.org/bots/api#chatjoinrequest)
 */
@Serializable
data class ChatJoinRequest(
    val chat: Chat,
)
