package uk.matvey.telek.demo

import mu.KotlinLogging
import uk.matvey.telek.Bot
import uk.matvey.telek.ReplyMarkup.InlineKeyboardButton.Companion.data
import uk.matvey.telek.ReplyMarkup.InlineKeyboardButton.Companion.url
import uk.matvey.telek.Update

private val log = KotlinLogging.logger("processIkbCommand")

suspend fun processIkbCommand(update: Update, bot: Bot) {
    bot.sendMessage(
        chatId = update.message().chat.id,
        text = "Inline keyboard",
        inlineKeyboard = listOf(
            listOf(
                url("Open matvey.uk", "https://matvey.uk"),
            ),
            listOf(
                data("Update message text", "ikb/update-message-text"),
            ),
            listOf(
                data("Remove inline keyboard", "ikb/remove"),
            ),
        )
    ).let { log.info { "sendMessage: $it" } }
}