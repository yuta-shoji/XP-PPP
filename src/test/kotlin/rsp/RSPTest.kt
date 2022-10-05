package rsp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RSPTest {
    @Test
    fun `rock vs scissors = p1 wins`() {
        //arrange
        val game = Game()


        //action
        val result = game.play(Throw.ROCK, Throw.SCISSORS)


        //assert
        assertEquals(GameResult.P1_WINS, result)
    }

    @Test
    fun `scissors vs rock = p2 wins`() {
        //arrange
        val game = Game()


        //action
        val result = game.play(Throw.SCISSORS, Throw.ROCK)


        //assert
        assertEquals(GameResult.P2_WINS, result)
    }
}