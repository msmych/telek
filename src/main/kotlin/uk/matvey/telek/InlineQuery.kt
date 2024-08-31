package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [InlineQuery](https://core.telegram.org/bots/api#inlinequery)
 */
@Serializable
data class InlineQuery(
    val id: String,
)
