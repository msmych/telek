package uk.matvey.telek.demo

import uk.matvey.telek.Bot
import uk.matvey.telek.Update

suspend fun processUpdate(update: Update, bot: Bot) {
    if (update.message?.text == "/start") {
        processStartCommand(update, bot)
        return
    }
}
