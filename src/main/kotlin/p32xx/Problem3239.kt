package p32xx

import util.expect

fun main() {
    class Solution {
        fun minFlips(grid: Array<IntArray>): Int {
            var result = intArrayOf(0, 0)

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (grid[r][row.lastIndex - c] != num) {
                        result[0]++
                    }

                    if (grid[grid.lastIndex - r][c] != num) {
                        result[1]++
                    }
                }
            }

            return result.min() / 2
        }
    }

    expect {
        Solution().minFlips(arrayOf())
    }
}
