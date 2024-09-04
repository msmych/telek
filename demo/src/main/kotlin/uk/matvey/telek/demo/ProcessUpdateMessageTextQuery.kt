package uk.matvey.telek.demo

import mu.KotlinLogging
import uk.matvey.telek.Bot
import uk.matvey.telek.Update
import java.time.LocalDateTime

private val log = KotlinLogging.logger("processUpdateMessageTextQuery")

suspend fun processUpdateMessageTextQuery(update: Update, bot: Bot) {
    val callbackQuery = update.callbackQuery()
    val message = callbackQuery.message()
    bot.editMessage(
        message = message,
        text = message.text() + "\n\n${LocalDateTime.now()}: Updated",
    ).let { log.info { "editMessageText: $it" } }
    bot.answerCallbackQuery(callbackQuery.id).let { log.info { "answerCallbackQuery: $it" } }
}