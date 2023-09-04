package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumMinutes(grid: Array<IntArray>): Int {
            val f = LinkedList<Pair<Int, Int>>()
            val fires = Array(grid.size) { r ->
                Array(grid[r].size) { c ->
                    when (grid[r][c]) {
                        0 -> {
                            Int.MAX_VALUE - 1
                        }

                        1 -> {
                            f.add(r to c)
                            Int.MAX_VALUE - 1
                        }

                        else -> {
                            -1
                        }
                    }
                }
            }

            var turn = 0
            while (f.isNotEmpty()) {
                repeat(f.size) {
                    val (r, c) = f.poll()

                    if (turn < fires[r][c]) {
                        fires[r][c] = turn

                        arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter { (nr, nc) ->
                            grid.getOrNull(nr)?.getOrNull(nc) == 0
                        }.forEach { (nr, nc) ->
                            f.add(nr to nc)
                        }
                    }
                }
                turn++
            }

            fires[fires.lastIndex][fires[0].lastIndex]++

            var min = 0
            var max = 1000000000

            var result = -1

            fun escape(turn: Int, pos: Pair<Int, Int>, visited: Array<IntArray>): Boolean {
                return when {
                    pos.first !in grid.indices || pos.second !in grid[0].indices -> {
                        false
                    }

                    turn >= fires[pos.first][pos.second] -> {
                        false
                    }

                    pos.first == grid.lastIndex && pos.second == grid[0].lastIndex -> {
                        true
                    }

                    turn >= visited[pos.first][pos.second] -> {
                        false
                    }

                    else -> {
                        visited[pos.first][pos.second] = turn
                        arrayOf(
                            pos.first - 1 to pos.second,
                            pos.first + 1 to pos.second,
                            pos.first to pos.second - 1,
                            pos.first to pos.second + 1
                        ).any {
                            escape(turn + 1, it, visited)
                        }
                    }
                }
            }

            while (min <= max) {
                val wait = (min + max) / 2
                if (escape(wait, 0 to 0, Array(grid.size) { IntArray(grid[0].size) { Int.MAX_VALUE } })) {
                    result = wait
                    min = wait + 1
                } else {
                    max = wait - 1
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumMinutes(
            arrayOf(
                intArrayOf(0, 2, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 2, 2, 1, 0),
                intArrayOf(0, 2, 0, 0, 1, 2, 0),
                intArrayOf(0, 0, 2, 2, 2, 0, 2),
                intArrayOf(0, 0, 0, 0, 0, 0, 0)
            )
        )
    }
}