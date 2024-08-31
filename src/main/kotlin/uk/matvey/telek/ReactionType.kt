package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [ReactionType](https://core.telegram.org/bots/api#reactiontype)
 */
@Serializable
data class ReactionType(
    val type: Type,
) {

    enum class Type {
        emoji,
        custom_emoji,
        paid,
    }
}
