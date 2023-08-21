package p12xx

import util.expect

fun main() {
    class Solution {
        fun shiftGrid(grid: Array<IntArray>, k: Int): List<List<Int>> {
            val m = grid.size
            val n = grid[0].size
            return List(m) { r ->
                List(n) { c ->
                    val targetC = ((c - k) % n + n) % n
                    val targetR = ((r - (k - c + n - 1) / n) % m + m) % m

                    grid[targetR][targetC]
                }
            }
        }
    }

    expect {
        Solution().shiftGrid(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9),
            ), 1
        ).joinToString("\n") { it.toList().toString() }
    }
}
