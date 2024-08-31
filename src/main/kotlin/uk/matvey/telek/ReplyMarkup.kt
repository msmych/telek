package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyMarkup(
    /**
     * [InlineKeyboardMarkup](https://core.telegram.org/bots/api#inlinekeyboardmarkup)
     */
    @SerialName("inline_keyboard")
    val inlineKeyboard: List<List<InlineKeyboardButton>>? = null,
) {

    /**
     * [InlineKeyboardButton](https://core.telegram.org/bots/api#inlinekeyboardbutton)
     */
    @Serializable
    data class InlineKeyboardButton(
        val text: String,
        val url: String? = null,
        @SerialName("callback_data")
        val callbackData: String? = null,
    )
}
