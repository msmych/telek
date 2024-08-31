package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [BusinessConnection](https://core.telegram.org/bots/api#businessconnection)
 */
@Serializable
data class BusinessConnection(
    val id: String,
)
