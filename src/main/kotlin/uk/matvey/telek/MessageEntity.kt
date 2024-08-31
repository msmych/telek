package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [MessageEntity](https://core.telegram.org/bots/api#messageentity)
 */
@Serializable
data class MessageEntity(
    val type: Type,
) {

    enum class Type {
        mention,
        hashtag,
        cashtag,
        bot_command,
        url,
        email,
        phone_number,
        bold,
        italic,
        underline,
        strikethrough,
        spoiler,
        blockquote,
        expandable_blockquote,
        code,
        pre,
        text_link,
        text_mention,
        custom_emoji,
    }
}
