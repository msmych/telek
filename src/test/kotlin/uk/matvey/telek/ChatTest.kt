package uk.matvey.telek

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import uk.matvey.kit.random.RandomKit.randomAlphabetic
import uk.matvey.kit.random.RandomKit.randomLong

class ChatTest {

    @Test
    fun `should validate chat id on creation`() {
        // when / then
        assertThatThrownBy { Chat.Id(null, null) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Either id or username must be provided")
        assertThatThrownBy { Chat.Id(randomLong(), randomAlphabetic()) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Either id or username must be provided")
    }
}