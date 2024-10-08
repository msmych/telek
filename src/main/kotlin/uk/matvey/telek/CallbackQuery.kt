package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [CallbackQuery](https://core.telegram.org/bots/api#callbackquery)
 */
@Serializable
data class CallbackQuery(
    val id: String,
    val from: User,
    val data: String? = null,
    val message: Message? = null,
) {

    fun data() = requireNotNull(data)

    fun message() = requireNotNull(message)
}
