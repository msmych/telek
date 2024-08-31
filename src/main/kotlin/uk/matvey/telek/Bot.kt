package uk.matvey.telek

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class Bot(
    token: String,
    private val longPollingSeconds: Int = 0,
    private val onUpdatesRetrievalException: (Exception) -> Unit = {},
    private val onUpdateProcessingException: (Exception) -> Unit = {},
) {

    val client = Client(token)

    suspend fun start(block: suspend (Update) -> Unit) = coroutineScope {
        while (isActive) {
            getUpdates()
                .onEach { update ->
                    try {
                        block(update)
                    } catch (e: Exception) {
                        onUpdateProcessingException(e)
                    }
                }
                .lastOrNull()
                ?.let { lastUpdate ->
                    getUpdates(lastUpdate)
                }
                ?: delay(100)
        }
    }

    suspend fun getUpdates(lastUpdate: Update? = null): List<Update> = try {
        client.getUpdates(
            offset = lastUpdate?.let { it.id + 1 },
            timeout = longPollingSeconds,
        )
            .mapTo<List<Update>>()
    } catch (e: ConnectTimeoutException) {
        listOf()
    } catch (e: HttpRequestTimeoutException) {
        listOf()
    } catch (e: Exception) {
        onUpdatesRetrievalException(e)
        listOf()
    }
}