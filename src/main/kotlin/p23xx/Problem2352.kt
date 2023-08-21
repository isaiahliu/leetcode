package p23xx

import util.expect

fun main() {
    class Solution {
        fun equalPairs(grid: Array<IntArray>): Int {
            val n = grid.size
            val rowCounts = hashMapOf<String, Int>()

            repeat(n) { r ->
                val str = StringBuilder()
                repeat(n) { c ->
                    str.append("${grid[r][c]},")
                }

                rowCounts[str.toString()] = (rowCounts[str.toString()] ?: 0) + 1
            }

            var result = 0
            repeat(n) { c ->
                val str = StringBuilder()
                repeat(n) { r ->
                    str.append("${grid[r][c]},")
                }

                result += rowCounts[str.toString()] ?: 0
            }

            return result
        }
    }

    expect {
        Solution().equalPairs(
            arrayOf(intArrayOf(3, 2, 1))
        )
    }
}
