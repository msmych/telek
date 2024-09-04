package uk.matvey.telek.demo

import mu.KotlinLogging
import uk.matvey.telek.Bot
import uk.matvey.telek.Update

private val log = KotlinLogging.logger("processRemoveInlineKeyboardQuery")

suspend fun processRemoveInlineKeyboardQuery(update: Update, bot: Bot) {
    val callbackQuery = update.callbackQuery()
    val message = callbackQuery.message()
    bot.editMessage(
        message = message,
        inlineKeyboard = listOf(listOf()),
    ).let { log.info { "editMessage: $it" } }
    bot.answerCallbackQuery(callbackQuery.id).let { log.info { "answerCallbackQuery: $it" } }
}