package uk.matvey.telek.apoche

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import uk.matvey.kit.json.JsonKit.jsonSerialize
import uk.matvey.telek.Bot
import uk.matvey.telek.ParseMode.MarkdownV2

private val log = KotlinLogging.logger("ApocheBot")

fun main(args: Array<String>) {
    val bot = Bot(
        token = args[0],
    )
    runBlocking {
        val job = CoroutineScope(Dispatchers.IO).launch {
            bot.start { update ->
                log.info { "Received $update" }
                if (update.message != null) {
                    val message = update.message()
                    bot.sendMessage(
                        chatId = message.chat.id,
                        text = """
                            Message
                            
                            ```
                            ${jsonSerialize(update)}
                            ```
                        """.trimIndent(),
                        parseMode = MarkdownV2,
                        replyTo = message.id,
                    ).let { log.info { "Sent $it" } }
                }
            }
        }
        log.info { "ApocheBot started" }
        job.join()
    }
}