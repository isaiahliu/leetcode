package p31xx

import util.expect

fun main() {
    class Solution {
        fun canMakeSquare(grid: Array<CharArray>): Boolean {
            (0 until grid.lastIndex).forEach { r ->
                val row = grid[r]
                (0 until row.lastIndex).forEach { c ->
                    if (arrayOf(r to c, r to c + 1, r + 1 to c, r + 1 to c + 1).sumOf {
                            (if (grid[it.first][it.second] == 'W') 0 else 1).toInt()
                        } != 2) {
                        return true
                    }
                }
            }
            return false
        }
    }

    expect {
        Solution().canMakeSquare(
            arrayOf()
        )
    }
}
