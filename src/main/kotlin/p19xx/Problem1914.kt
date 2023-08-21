package p19xx

import util.expect

fun main() {
    class Solution {
        fun rotateGrid(grid: Array<IntArray>, k: Int): Array<IntArray> {
            val row = grid.size
            val column = grid[0].size

            val result = Array(grid.size) {
                IntArray(grid[it].size)
            }

            repeat(grid.size.coerceAtMost(grid[0].size) / 2) { level ->
                val pos = arrayListOf(level to level)

                var deltaR = 0
                var deltaC = 1

                var r = level + deltaR
                var c = level + deltaC

                do {
                    pos.add(r to c)

                    if ((r == level || r == row - level - 1) && (c == level || c == column - level - 1)) {
                        val t = deltaR
                        deltaR = deltaC
                        deltaC = -t
                    }

                    r += deltaR
                    c += deltaC
                } while (r != level || c != level)

                pos.forEachIndexed { index, (r, c) ->
                    val (targetR, targetC) = pos[(index + k) % pos.size]
                    result[r][c] = grid[targetR][targetC]
                }
            }

            return result
        }
    }

    expect {
        Solution().rotateGrid(
            arrayOf(
                intArrayOf(4, 5, 8, 9, 4, 2, 4, 7, 2, 4),
                intArrayOf(7, 1, 9, 6, 6, 1, 4, 5, 7, 7),
                intArrayOf(7, 1, 5, 1, 1, 7, 10, 1, 3, 1),
                intArrayOf(7, 2, 2, 5, 2, 6, 6, 4, 7, 7),
                intArrayOf(1, 2, 3, 8, 4, 7, 6, 9, 6, 2),
                intArrayOf(5, 10, 3, 4, 7, 2, 7, 5, 3, 10),
            ), 4
        )
    }
}