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
    )

    enum class Type {
        private,
        group,
        supergroup,
        channel,
    }
}
