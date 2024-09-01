package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [Chat](https://core.telegram.org/bots/api#chat)
 */
@Serializable
data class Chat(
    val id: Long,
    val type: Type,
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

    enum class Type {
        private,
        group,
        supergroup,
        channel,
    }
}
