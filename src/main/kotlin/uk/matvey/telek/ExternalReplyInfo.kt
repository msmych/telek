package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [ExternalReplyInfo](https://core.telegram.org/bots/api#externalreplyinfo)
 */
@Serializable
data class ExternalReplyInfo(
    val origin: MessageOrigin,
)
