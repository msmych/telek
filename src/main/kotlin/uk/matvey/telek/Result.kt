package uk.matvey.telek

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
data class Result(
    val ok: Boolean,
    val result: JsonElement? = null,
    val description: String? = null,
) {

    inline fun <reified T> parse(): T {
        if (!ok) {
            throw RequestException(description)
        }
        return Json.decodeFromJsonElement(requireNotNull(result))
    }
}
