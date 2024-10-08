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
    val text: String? = null,
    @SerialName("reply_markup")
    val replyMarkup: ReplyMarkup? = null,
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
    @SerialName("forward_origin")
    val forwardOrigin: MessageOrigin? = null,
    @SerialName("is_topic_message")
    val isTopicMessage: Boolean? = null,
    @SerialName("is_automatic_forward")
    val isAutomaticForward: Boolean? = null,
    @SerialName("reply_to_message")
    val replyToMessage: Message? = null,
    @SerialName("external_reply")
    val externalReply: ExternalReplyInfo? = null,
    val quote: TextQuote? = null,
    @SerialName("reply_to_story")
    val replyToStory: Story? = null,
    @SerialName("via_bot")
    val viaBot: User? = null,
    @SerialName("edit_date")
    val editDate: Int? = null,
    @SerialName("has_protected_content")
    val hasProtectedContent: Boolean? = null,
    @SerialName("is_from_offline")
    val isFromOffline: Boolean? = null,
    @SerialName("media_group_id")
    val mediaGroupId: String? = null,
    @SerialName("author_signature")
    val authorSignature: String? = null,
    val entities: List<MessageEntity>? = null,
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,
    @SerialName("effect_id")
    val effectId: String? = null,
) {

    data class Id(
        val chatId: Chat.Id,
        val messageId: Int,
    )

    fun messageId() = Id(chat.chatId(), id)

    fun text() = requireNotNull(text)

    fun from() = requireNotNull(from)
}
