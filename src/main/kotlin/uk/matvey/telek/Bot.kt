package uk.matvey.telek

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import uk.matvey.telek.Chat.Id.Companion.chatId
import uk.matvey.telek.ReplyMarkup.InlineKeyboardButton

class Bot(
    token: String,
    private val longPollingSeconds: Int = 0,
    private val onUpdatesRetrievalException: (Exception) -> Unit = {},
    private val onUpdateProcessingException: (Exception) -> Unit = {},
    getUpdatesMinTimeout: Long = 5000,
) {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = false
    }

    val client = Client(token, json, getUpdatesMinTimeout)

    suspend fun start(block: suspend (Update) -> Unit) = coroutineScope {
        while (isActive) {
            getUpdates()
                .onEach { update ->
                    try {
                        block(update)
                    } catch (e: Exception) {
                        onUpdateProcessingException(e)
                    }
                }
                .lastOrNull()
                ?.let { lastUpdate ->
                    getUpdates(lastUpdate)
                }
        }
    }

    suspend fun getUpdates(confirmUpdate: Update? = null): List<Update> = try {
        client.getUpdates(
            offset = confirmUpdate?.let { it.id + 1 },
            timeout = longPollingSeconds,
        )
            .parse()
    } catch (_: ConnectTimeoutException) {
        listOf()
    } catch (_: HttpRequestTimeoutException) {
        listOf()
    } catch (e: Exception) {
        onUpdatesRetrievalException(e)
        listOf()
    }

    suspend fun getMe(): User {
        return client.getMe().parse()
    }

    suspend fun logOut(): Boolean {
        return client.logOut().parse()
    }

    suspend fun sendMessage(
        chatId: Long,
        text: String,
        parseMode: ParseMode? = null,
        inlineKeyboard: List<List<InlineKeyboardButton>>? = null,
        replyTo: Int? = null,
    ): Message {
        return client.sendMessage(
            chatId = chatId(chatId),
            text = text,
            parseMode = parseMode,
            replyMarkup = inlineKeyboard?.let(::ReplyMarkup),
            replyParameters = replyTo?.let(::ReplyParameters),
        )
            .parse()
    }

    suspend fun editMessage(
        messageId: Message.Id,
        text: String,
        inlineKeyboard: List<List<InlineKeyboardButton>>? = null,
    ): Message {
        return client.editMessageText(
            messageId = messageId,
            text = text,
            replyMarkup = inlineKeyboard?.let(::ReplyMarkup),
        )
            .parse()
    }

    suspend fun editMessageInlineKeyboard(
        messageId: Message.Id,
        inlineKeyboard: List<List<InlineKeyboardButton>>,
    ): Message {
        return client.editMessageReplyMarkup(
            messageId = messageId,
            replyMarkup = ReplyMarkup(inlineKeyboard),
        )
            .parse()
    }

    suspend fun answerCallbackQuery(id: String): Boolean = client.answerCallbackQuery(id).parse()

    private inline fun <reified T> Result.parse(): T {
        if (!ok) {
            throw RequestException(description)
        }
        return json.decodeFromJsonElement(requireNotNull(result))
    }
}