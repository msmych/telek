package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [ShippingQuery](https://core.telegram.org/bots/api#shippingquery)
 */
@Serializable
data class ShippingQuery(
    val id: String,
)
