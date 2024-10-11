package uk.matvey.telek.demo

import mu.KotlinLogging
import uk.matvey.telek.Bot
import uk.matvey.telek.ParseMode.MarkdownV2
import uk.matvey.telek.Update

private val log = KotlinLogging.logger("processGetMeCommand")

suspend fun processGetMeCommand(update: Update, bot: Bot) {
    val me = bot.getMe()
    bot.sendMessage(
        chatId = update.message().chat.id,
        text = "```\n$me\n```",
        parseMode = MarkdownV2,
    )
        .let { log.info { "sendMessage: $it" } }
}
