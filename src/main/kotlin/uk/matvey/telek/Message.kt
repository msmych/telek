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
    val date: Int,
    val chat: Chat,
    @SerialName("message_thread_id")
    val threadId: Int? = null,
    val from: User? = null,
    @SerialName("sender_chat")
    val senderChat: Chat? = null,
    @SerialName("sender_boost_count")
    val senderBoostCount: Int? = null,
    @SerialName("sender_business_bot")
    val senderBusinessBot: User? = null,
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,
)
