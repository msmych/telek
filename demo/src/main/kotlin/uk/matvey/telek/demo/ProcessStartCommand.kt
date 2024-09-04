package uk.matvey.telek.demo

import mu.KotlinLogging
import uk.matvey.telek.Bot
import uk.matvey.telek.Update

private val log = KotlinLogging.logger("processStartCommand")

suspend fun processStartCommand(update: Update, bot: Bot) {
    val message = update.message()
    bot.sendMessage(
        chatId = message.chat.id,
        text = "Hello, ${message.from().firstName}",
    ).let { log.info { "sendMessage: $it" } }
}