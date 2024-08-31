package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [Poll](https://core.telegram.org/bots/api#poll)
 */
@Serializable
data class Poll(
    val id: String,
)
