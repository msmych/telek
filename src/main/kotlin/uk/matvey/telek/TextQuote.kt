package uk.matvey.telek

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [TextQuote](https://www.w3.org/TR/annotation-model/#textquote)
 */
@Serializable
data class TextQuote(
    val text: String,
    val position: Int,
    val entities: List<MessageEntity>? = null,
    @SerialName("is_manual")
    val isManual: Boolean? = null,
)
