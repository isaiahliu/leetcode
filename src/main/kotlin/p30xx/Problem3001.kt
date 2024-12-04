package p30xx

import util.expect

fun main() {
    class Solution {
        fun minMovesToCaptureTheQueen(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int): Int {
            arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).forEach { (deltaX, deltaY) ->
                var rookX = a
                var rookY = b

                while (rookX in 1..8 && rookY in 1..8) {
                    when {
                        rookX == c && rookY == d -> {
                            break
                        }

                        rookX == e && rookY == f -> {
                            return 1
                        }

                        else -> {
                            rookX += deltaX
                            rookY += deltaY
                        }
                    }
                }
            }


            arrayOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1).forEach { (deltaX, deltaY) ->
                var bishopX = c
                var bishopY = d

                while (bishopX in 1..8 && bishopY in 1..8) {
                    when {
                        bishopX == a && bishopY == b -> {
                            break
                        }

                        bishopX == e && bishopY == f -> {
                            return 1
                        }

                        else -> {
                            bishopX += deltaX
                            bishopY += deltaY
                        }
                    }
                }
            }

            return 2
        }
    }

    expect {
        Solution().minMovesToCaptureTheQueen(
            9, 1, 1, 1, 1, 1
        )
    }
}
