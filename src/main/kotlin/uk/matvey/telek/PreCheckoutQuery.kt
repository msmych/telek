package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [PreCheckoutQuery](https://core.telegram.org/bots/api#precheckoutquery)
 */
@Serializable
data class PreCheckoutQuery(
    val id: String,
)
