package uk.matvey.telek.demo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import uk.matvey.telek.Bot

private val log = KotlinLogging.logger("Demo")

fun main(args: Array<String>) {
    val bot = Bot(
        args[0],
        onUpdatesRetrievalException = { log.error(it) { "Failed to fetch updates" } },
        onUpdateProcessingException = { log.error(it) { "Failed to process update" } },
    )
    val job = CoroutineScope(Dispatchers.IO).launch {
        bot.start { update ->
            processUpdate(update, bot)
        }
    }
    runBlocking {
        job.join()
    }
}