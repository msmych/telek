package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [MessageOrigin](https://core.telegram.org/bots/api#messageorigin)
 */
@Serializable
data class MessageOrigin(
    val type: Type,
    val date: Int,
) {

    enum class Type {
        user,
        hidden_user,
        chat,
        channel,
    }
}
