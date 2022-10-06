package rsp

class Game {
    fun play(p1: Throw, p2: Throw): GameResult {
        if (
            (p1 == Throw.ROCK && p2 == Throw.SCISSORS)
            || (p1 == Throw.PAPER && p2 == Throw.ROCK)
            || (p1 == Throw.SCISSORS && p2 == Throw.PAPER)
        ) {
            return GameResult.P1_WINS
        } else if (p1 == p2) {
            return GameResult.TIE
        }
        return GameResult.P2_WINS
    }
}
