package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [ChatBoostUpdated](https://core.telegram.org/bots/api#chatboostupdated)
 */
@Serializable
data class ChatBoostUpdated(
    val chat: Chat,
)
