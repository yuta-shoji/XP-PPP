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

    @Test
    fun `paper vs rock = p1 wins`() {
        //arrange
        val game = Game()


        //action
        val result = game.play(Throw.PAPER, Throw.ROCK)


        //assert
        assertEquals(GameResult.P1_WINS, result)
    }

    @Test
    fun `rock vs paper = p2 wins`() {
        val game = Game()


        val result = game.play(Throw.ROCK, Throw.PAPER)


        assertEquals(GameResult.P2_WINS, result)
    }

    @Test
    fun `scissors vs paper = p1 wins`() {
        val game = Game()


        val result = game.play(Throw.SCISSORS, Throw.PAPER)


        assertEquals(GameResult.P1_WINS, result)
    }

    @Test
    fun `paper vs scissors = p2 wins`() {
        val game = Game()


        val result = game.play(Throw.PAPER, Throw.SCISSORS)


        assertEquals(GameResult.P2_WINS, result)
    }

    @Test
    fun `rock vs rock = Tie!`() {
        val game = Game()


        val result = game.play(Throw.ROCK, Throw.ROCK)


        assertEquals(GameResult.TIE, result)
    }

    @Test
    fun `scissors vs scissors = Tie!`() {
        val game = Game()


        val result = game.play(Throw.SCISSORS, Throw.SCISSORS)


        assertEquals(GameResult.TIE, result)
    }

    @Test
    fun `paper vs paper = Tie!`() {
        val game = Game()


        val result = game.play(Throw.PAPER, Throw.PAPER)


        assertEquals(GameResult.TIE, result)
    }
}