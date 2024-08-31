package uk.matvey.telek

import kotlinx.serialization.Serializable

/**
 * [LinkPreviewOptions](https://core.telegram.org/bots/api#linkpreviewoptions)
 */
@Serializable
data class LinkPreviewOptions(
    val idDisabled: Boolean? = null,
)
