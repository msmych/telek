package uk.matvey.telek.demo

import uk.matvey.telek.Bot
import uk.matvey.telek.Update

suspend fun processStartCommand(update: Update, bot: Bot) {
    val message = update.message()
    bot.sendMessage(
        chatId = message.chat.id,
        text = "Hello, ${message.from().firstName}",
    )
}