package uk.matvey.telek

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import uk.matvey.telek.Chat.Id.Companion.chatId
import uk.matvey.telek.ReplyMarkup.InlineKeyboardButton

class Bot(
    token: String,
    private val longPollingSeconds: Int = 0,
    private val onUpdatesRetrievalException: (Exception) -> Unit = {},
    private val onUpdateProcessingException: (Exception) -> Unit = {},
) {

    val client = Client(token)

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
                ?: delay(100)
        }
    }

    suspend fun getUpdates(confirmUpdate: Update? = null): List<Update> = try {
        client.getUpdates(
            offset = confirmUpdate?.let { it.id + 1 },
            timeout = longPollingSeconds,
        )
            .mapTo()
    } catch (e: ConnectTimeoutException) {
        listOf()
    } catch (e: HttpRequestTimeoutException) {
        listOf()
    } catch (e: Exception) {
        onUpdatesRetrievalException(e)
        listOf()
    }

    suspend fun sendMessage(
        chatId: Long,
        text: String,
        parseMode: ParseMode? = null,
        inlineKeyboard: List<List<InlineKeyboardButton>>? = null,
    ): Message {
        return client.sendMessage(
            chatId = chatId(chatId),
            text = text,
            parseMode = parseMode,
            replyMarkup = inlineKeyboard?.let(::ReplyMarkup),
        )
            .mapTo()
    }

    suspend fun editMessage(
        message: Message,
        text: String? = null,
        inlineKeyboard: List<List<InlineKeyboardButton>>? = null,
    ): Message {
        return client.editMessageText(
            chatId = chatId(message.chat.id),
            messageId = message.id,
            text = text ?: message.text(),
            replyMarkup = inlineKeyboard?.let(::ReplyMarkup) ?: message.replyMarkup,
        )
            .mapTo()
    }

    suspend fun answerCallbackQuery(id: String) = client.answerCallbackQuery(id)
}