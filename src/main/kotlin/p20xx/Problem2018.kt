package p20xx

import util.expect

fun main() {
    class Solution {
        fun placeWordInCrossword(board: Array<CharArray>, word: String): Boolean {
            val sums = Array(board.size) {
                Array(board[it].size) {
                    //top, left, bottom, right
                    intArrayOf(0, 0, 0, 0)
                }
            }

            board.forEachIndexed { r, row ->
                row.forEachIndexed { c, ch ->
                    if (ch != '#') {
                        val sum = sums[r][c]

                        var top = sums.getOrNull(r - 1)?.get(c)?.get(0) ?: 0
                        var left = sums.getOrNull(r)?.getOrNull(c - 1)?.get(1) ?: 0
                        var bottom = sums.getOrNull(r - 1)?.get(c)?.get(2) ?: 0
                        var right = sums.getOrNull(r)?.getOrNull(c - 1)?.get(3) ?: 0

                        if (ch != ' ' && ch != word.getOrNull(top)) {
                            top = word.length
                        }
                        top++
                        sum[0] = top

                        if (ch != ' ' && ch != word.getOrNull(left)) {
                            left = word.length
                        }
                        left++
                        sum[1] = left

                        if (ch != ' ' && ch != word.getOrNull(word.lastIndex - bottom)) {
                            bottom = word.length
                        }
                        bottom++
                        sum[2] = bottom

                        if (ch != ' ' && ch != word.getOrNull(word.lastIndex - right)) {
                            right = word.length + 1
                        }
                        right++
                        sum[3] = right

                        if ((top == word.length || bottom == word.length) && board.getOrNull(r + 1)?.get(c)
                                ?.takeIf { it != '#' } == null
                        ) {
                            return true
                        }

                        if ((left == word.length || right == word.length) && board.getOrNull(r)?.getOrNull(c + 1)
                                ?.takeIf { it != '#' } == null
                        ) {
                            return true
                        }
                    }
                }
            }

            return false
        }
    }

    expect {
        Solution().placeWordInCrossword(
            arrayOf(
                charArrayOf('z', ' '),
                charArrayOf('z', ' '),
            ), "a"
        )
    }
}