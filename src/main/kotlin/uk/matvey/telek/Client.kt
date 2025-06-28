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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.put

/**
 *  [Telegram Bots API](https://core.telegram.org/bots/api)
 */
class Client(
    token: String,
    private val json: Json,
    private val getUpdatesMinTimeout: Long = 5000,
) {

    private val baseUrl = "https://api.telegram.org/bot$token"

    private val client = HttpClient(CIO) {
        install(HttpTimeout)
        install(ContentNegotiation) {
            json(json)
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
                requestTimeoutMillis = (1000L * (timeout ?: 0)).coerceAtLeast(getUpdatesMinTimeout)
            }
            offset?.let { url.parameters.append("offset", it.toString()) }
            limit?.let { url.parameters.append("limit", it.toString()) }
            timeout?.let { url.parameters.append("timeout", it.toString()) }
            allowedUpdates?.let { url.parameters.append("allowed_updates", json.encodeToString(it)) }
        }
            .body()
    }

    /**
     * [getMe](https://core.telegram.org/bots/api#getme)
     */
    suspend fun getMe(): Result {
        return client.get("$baseUrl/getMe").body()
    }

    /**
     * [logOut](https://core.telegram.org/bots/api#logout)
     */
    suspend fun logOut(): Result {
        return client.get("$baseUrl/logOut").body()
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
                    putChatId(chatId)
                    put("text", text)
                    businessConnectionId?.let { put("business_connection_id", it) }
                    messageThreadId?.let { put("message_thread_id", it) }
                    parseMode?.let { put("parse_mode", it.name) }
                    entities?.let { put("entities", json.encodeToJsonElement(it)) }
                    linkPreviewOptions?.let { put("link_preview_options", json.encodeToJsonElement(it)) }
                    disableNotification?.let { put("disable_notification", it) }
                    protectContent?.let { put("protect_content", it) }
                    messageEffectId?.let { put("message_effect_id", it) }
                    replyParameters?.let { put("reply_parameters", json.encodeToJsonElement(it)) }
                    replyMarkup?.let { put("reply_markup", json.encodeToJsonElement(it)) }
                }
            )
        }
            .body()
    }

    /**
     * [editMessageText](https://core.telegram.org/bots/api#editmessagetext)
     */
    suspend fun editMessageText(
        text: String,
        messageId: Message.Id? = null,
        inlineMessageId: Int? = null,
        businessConnectionId: String? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Result {
        return client.post("$baseUrl/editMessageText") {
            setBody(
                buildJsonObject {
                    putChatId(messageId?.chatId)
                    put("text", text)
                    messageId?.let { put("message_id", it.messageId) }
                    businessConnectionId?.let { put("business_connection_id", it) }
                    inlineMessageId?.let { put("inline_message_id", it) }
                    parseMode?.let { put("parse_mode", it.name) }
                    entities?.let { put("entities", json.encodeToJsonElement(it)) }
                    linkPreviewOptions?.let { put("link_preview_options", json.encodeToJsonElement(it)) }
                    replyMarkup?.let { put("reply_markup", json.encodeToJsonElement(it)) }
                }
            )
        }
            .body()
    }

    /**
     * [editMessageReplyMarkup](https://core.telegram.org/bots/api#editmessagereplymarkup)
     */
    suspend fun editMessageReplyMarkup(
        messageId: Message.Id? = null,
        inlineMessageId: Int? = null,
        businessConnectionId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Result {
        return client.post("$baseUrl/editMessageReplyMarkup") {
            setBody(
                buildJsonObject {
                    putChatId(messageId?.chatId)
                    messageId?.let { put("message_id", it.messageId) }
                    inlineMessageId?.let { put("inline_message_id", it) }
                    businessConnectionId?.let { put("business_connection_id", it) }
                    replyMarkup?.let { put("reply_markup", json.encodeToJsonElement(it)) }
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

    private fun JsonObjectBuilder.putChatId(chatId: Chat.Id?) {
        chatId?.let {
            if (it.id != null) {
                put("chat_id", it.id)
            } else {
                put("chat_id", it.username)
            }
        }
    }
}
