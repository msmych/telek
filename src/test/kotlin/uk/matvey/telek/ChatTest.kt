package uk.matvey.telek

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ChatTest {

    @Test
    fun `should validate chat id on creation`() {
        // when / then
        assertThatThrownBy { Chat.Id(null, null) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Either id or username must be provided")
        assertThatThrownBy { Chat.Id(Random.nextLong(), Random.nextLong().toString()) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Either id or username must be provided")
    }
}
