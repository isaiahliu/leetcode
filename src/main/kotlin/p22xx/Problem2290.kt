package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumObstacles(grid: Array<IntArray>): Int {
            val removeCount = Array(grid.size) {
                IntArray(grid[it].size) {
                    Int.MAX_VALUE
                }
            }

            val queue = PriorityQueue<Pair<Pair<Int, Int>, Int>>(compareBy { it.second })
            queue.add(0 to 0 to 0)

            while (queue.isNotEmpty()) {
                val (p, cost) = queue.poll()

                val (r, c) = p

                if (r == grid.lastIndex && c == grid[0].lastIndex) {
                    return cost
                } else if (cost < removeCount[r][c]) {
                    removeCount[r][c] = cost

                    arrayOf(r + 1 to c, r - 1 to c, r to c + 1, r to c - 1).forEach { (newR, newC) ->
                        grid.getOrNull(newR)?.getOrNull(newC)?.also {
                            queue.add(newR to newC to cost + it)
                        }
                    }
                }
            }

            return removeCount.last().last()
        }
    }

    expect(3) {
        Solution().minimumObstacles(
            arrayOf()
        )
    }
}