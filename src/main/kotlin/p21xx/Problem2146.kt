package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun highestRankedKItems(grid: Array<IntArray>, pricing: IntArray, start: IntArray, k: Int): List<List<Int>> {
            val priceRange = pricing[0]..pricing[1]

            val queue = PriorityQueue(compareBy<Pair<Pair<Int, Int>, Int>> { it.second }
                .thenBy { grid[it.first.first][it.first.second] }
                .thenBy { it.first.first }.thenBy { it.first.second })

            val visited = Array(grid.size) {
                BooleanArray(grid[it].size)
            }
            visited[start[0]][start[1]] = true

            val tasks = LinkedList<Pair<Int, Int>>()
            tasks.add(start[0] to start[1])

            var step = 0
            while (tasks.isNotEmpty()) {
                repeat(tasks.size) {
                    val (r, c) = tasks.poll()

                    if (grid[r][c] > 1 && grid[r][c] in priceRange) {
                        queue.offer(r to c to step)
                    }

                    arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).forEach { (dr, dc) ->
                        val newR = r + dr
                        val newC = c + dc
                        if (grid.getOrNull(newR)?.getOrNull(newC)?.takeIf { it > 0 } != null && !visited[newR][newC]) {
                            visited[newR][newC] = true
                            tasks.add(newR to newC)
                        }
                    }
                }

                step++
            }

            val result = arrayListOf<List<Int>>()

            repeat(queue.size.coerceAtMost(k)) {
                result.add(queue.poll().first.let { (r, c) -> listOf(r, c) })
            }

            return result
        }
    }

    expect {
        Solution().highestRankedKItems(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(0, 0, 1),
                intArrayOf(2, 3, 4),
            ), intArrayOf(2, 3), intArrayOf(0, 0), 3
        )
    }
}