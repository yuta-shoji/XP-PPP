package rsp

class Game {
    fun play(p1: Throw, p2: Throw): GameResult {
        if (p1 == Throw.ROCK) {
            return GameResult.P1_WINS
        }
        return GameResult.P2_WINS
    }
}
