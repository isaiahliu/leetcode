package p04xx

import util.expect

fun main() {
    class Solution {
        fun countBattleships(board: Array<CharArray>): Int {
            var result = 0
            board.forEachIndexed { r, row ->
                row.forEachIndexed { c, n ->
                    if (n == 'X') {
                        if (board.getOrNull(r - 1)?.getOrNull(c) != 'X' && board.getOrNull(r)
                                ?.getOrNull(c - 1) != 'X'
                        ) {
                            result++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countBattleships(
            arrayOf()
        )
    }
}


