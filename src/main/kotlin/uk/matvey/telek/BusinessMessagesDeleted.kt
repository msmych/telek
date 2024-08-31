package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [BusinessMessagesDeleted](https://core.telegram.org/bots/api#businessmessagesdeleted)
 */
@Serializable
data class BusinessMessagesDeleted(
    val businessConnectionId: String,
)
