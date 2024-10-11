package uk.matvey.telek.demo

import mu.KotlinLogging
import uk.matvey.telek.Bot
import uk.matvey.telek.Update

private val log = KotlinLogging.logger("processUpdate")

suspend fun processUpdate(update: Update, bot: Bot) {
    log.info { "Processing update: $update" }
    if (update.message?.text == "/start") {
        processStartCommand(update, bot)
        return
    }
    if (update.message?.text == "/getme") {
        processGetMeCommand(update, bot)
        return
    }
    if (update.message?.text == "/ikb") {
        processIkbCommand(update, bot)
        return
    }
    if (update.callbackQuery?.data == "ikb/update-message-text") {
        processUpdateMessageTextQuery(update, bot)
        return
    }
    if (update.callbackQuery?.data == "ikb/remove") {
        processRemoveInlineKeyboardQuery(update, bot)
        return
    }
}
