package rsp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RSPTest {
    @Test
    fun `p1 wins`() {
        val game = Game()

        val result = game.play(Throw.ROCK, Throw.SCISSORS)

        assertEquals(result, GameResult.P1_WINS)
    }
}