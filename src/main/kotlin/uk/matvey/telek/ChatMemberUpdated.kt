package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [ChatMemberUpdated](https://core.telegram.org/bots/api#chatmemberupdated)
 */
@Serializable
data class ChatMemberUpdated(
    val chat: Chat,
)
