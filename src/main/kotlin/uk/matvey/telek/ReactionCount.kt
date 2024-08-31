package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * (ReactionCount)[https://core.telegram.org/bots/api#reactioncount)
 */
@Serializable
data class ReactionCount(
    val type: ReactionType,
    @SerialName("total_count")
    val totalCount: Int,
)
