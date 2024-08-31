package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [ChosenInlineResult](https://core.telegram.org/bots/api#choseninlineresult)
 */
@Serializable
data class ChosenInlineResult(
    @SerialName("result_id")
    val resultId: String,
)
