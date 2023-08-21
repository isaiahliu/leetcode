package p00xx

import util.expect

fun main() {
    class Solution {
        fun exist(board: Array<CharArray>, word: String): Boolean {
            fun walk(pos: Pair<Int, Int>, matchCount: Int, route: Set<Pair<Int, Int>>): Boolean {
                if (matchCount != route.size) {
                    return false
                }

                if (matchCount == word.length) {
                    return true
                }

                return arrayOf(
                    pos.first - 1 to pos.second,
                    pos.first to pos.second - 1,
                    pos.first + 1 to pos.second,
                    pos.first to pos.second + 1
                ).filter { (r, c) -> board.getOrNull(r)?.getOrNull(c) == word[matchCount] }.any {
                    walk(it, matchCount + 1, route + it)
                }
            }
            board.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, c ->
                    if (c == word[0]) {
                        if (walk(rowIndex to columnIndex, 1, setOf(rowIndex to columnIndex))) {
                            return true
                        }
                    }
                }
            }

            return false
        }
    }

    expect {
        Solution().exist(emptyArray(), "")
    }
}

