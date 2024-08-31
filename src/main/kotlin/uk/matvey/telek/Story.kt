package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [Story](https://core.telegram.org/bots/api#story)
 */
@Serializable
data class Story(
    val chat: Chat,
    val id: Int,
)
