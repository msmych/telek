package uk.matvey.telek

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import uk.matvey.kit.json.JsonKit.JSON
import uk.matvey.kit.json.JsonKit.jsonArraySerialize
import uk.matvey.kit.json.JsonKit.jsonObjectSerialize

/**
 *  [Telegram Bots API](https://core.telegram.org/bots/api)
 */
class Client(
    token: String,
) {

    private val baseUrl = "https://api.telegram.org/bot$token"

    private val client = HttpClient(CIO) {
        install(HttpTimeout)
        install(ContentNegotiation) {
            json(JSON)
        }
        install(DefaultRequest) {
            contentType(Application.Json)
        }
    }

    /**
     *  [getUpdates](https://core.telegram.org/bots/api#getupdates)
     */
    suspend fun getUpdates(
        offset: Int? = null,
        limit: Int? = null,
        timeout: Int? = null,
        allowedUpdates: List<String>? = null,
    ): Result {
        return client.get("$baseUrl/getUpdates") {
            timeout {
                requestTimeoutMillis = (1000L * (timeout ?: 0)).coerceAtLeast(5000)
            }
            offset?.let { url.parameters.append("offset", it.toString()) }
            limit?.let { url.parameters.append("limit", it.toString()) }
            timeout?.let { url.parameters.append("timeout", it.toString()) }
            allowedUpdates?.let { url.parameters.append("allowed_updates", JSON.encodeToString(it)) }
        }
            .body()
    }

    /**
     *  [sendMessage](https://core.telegram.org/bots/api#sendmessage)
     */
    suspend fun sendMessage(
        chatId: Chat.Id,
        text: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Result {
        return client.post("$baseUrl/sendMessage") {
            setBody(
                buildJsonObject {
                    if (chatId.id != null) {
                         put("chat_id", chatId.id)
                    } else {
                        put("chat_id", chatId.username)
                    }
                    put("text", text)
                    businessConnectionId?.let { put("business_connection_id", it) }
                    messageThreadId?.let { put("message_thread_id", it) }
                    parseMode?.let { put("parse_mode", it.name) }
                    entities?.let { put("entities", jsonArraySerialize(it)) }
                    linkPreviewOptions?.let { put("link_preview_options", jsonObjectSerialize(it)) }
                    disableNotification?.let { put("disable_notification", it) }
                    protectContent?.let { put("protect_content", it) }
                    messageEffectId?.let { put("message_effect_id", it) }
                    replyParameters?.let { put("reply_parameters", jsonObjectSerialize(it)) }
                    replyMarkup?.let { put("reply_markup", jsonObjectSerialize(it)) }
                }
            )
        }
            .body()
    }

    /**
     * [editMessageText](https://core.telegram.org/bots/api#editmessagetext)
     */
    suspend fun editMessageText(
        chatId: Chat.Id,
        messageId: Int,
        text: String,
        businessConnectionId: String? = null,
        inlineMessageId: Int? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Result {
        return client.post("$baseUrl/editMessageText") {
            setBody(
                buildJsonObject {
                    if (chatId.id != null) {
                        put("chat_id", chatId.id)
                    } else {
                        put("chat_id", chatId.username)
                    }
                    put("message_id", messageId)
                    put("text", text)
                    businessConnectionId?.let { put("business_connection_id", it) }
                    inlineMessageId?.let { put("inline_message_id", it) }
                    parseMode?.let { put("parse_mode", it.name) }
                    entities?.let { put("entities", jsonArraySerialize(it)) }
                    linkPreviewOptions?.let { put("link_preview_options", jsonObjectSerialize(it)) }
                    replyMarkup?.let { put("reply_markup", jsonObjectSerialize(it)) }
                }
            )
        }
            .body()
    }

    /**
     * [answerCallbackQuery](https://core.telegram.org/bots/api#answercallbackquery)
     */
    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Int? = null,
    ): Result {
        return client.post("$baseUrl/answerCallbackQuery") {
            setBody(
                buildJsonObject {
                    put("callback_query_id", callbackQueryId)
                    text?.let { put("text", it) }
                    showAlert?.let { put("show_alert", it) }
                    url?.let { put("url", it) }
                    cacheTime?.let { put("cache_time", it) }
                }
            )
        }
            .body()
    }
}