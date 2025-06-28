package uk.matvey.telek

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Result(
    val ok: Boolean,
    val result: JsonElement? = null,
    val description: String? = null,
)
