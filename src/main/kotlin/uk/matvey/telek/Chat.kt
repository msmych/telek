package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [Chat](https://core.telegram.org/bots/api#chat)
 */
@Serializable
data class Chat(
    val id: Long,
    val type: Type,
    val title: String? = null,
    val username: String? = null,
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("is_forum")
    val isForum: Boolean? = null,
) {

    data class Id(
        val id: Long?,
        val username: String?,
    ) {

        init {
            require((id == null) xor (username == null)) { "Either id or username must be provided" }
        }

        companion object {

            fun chatId(id: Long) = Id(id, null)

            fun chatId(username: String) = Id(null, username)
        }
    }

    fun chatId() = Id.chatId(id)

    enum class Type {
        private,
        group,
        supergroup,
        channel,
    }
}
