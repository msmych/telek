package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [PollAnswer](https://core.telegram.org/bots/api#pollanswer)
 */
@Serializable
data class PollAnswer(
    @SerialName("poll_id")
    val pollId: String,
)
