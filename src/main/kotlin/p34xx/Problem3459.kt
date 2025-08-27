package p34xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun lenOfVDiagonal(grid: Array<IntArray>): Int {
            val directions = arrayOf(
                1 to 1,
                1 to -1,
                -1 to -1,
                -1 to 1,
            )

            val initPos = hashSetOf<Pair<Int, Int>>()
            val cache = Array(grid.size) { r ->
                Array(grid[r].size) { c ->
                    if (grid[r][c] == 1) {
                        initPos.add(r to c)
                    }

                    Array(4) { intArrayOf(0, 0) }
                }
            }

            fun dfs(pos: Pair<Int, Int>, directionIndex: Int, turnCount: Int): Int {
                return if (cache[pos.first][pos.second][directionIndex][turnCount] > 0) {
                    cache[pos.first][pos.second][directionIndex][turnCount]
                } else {
                    var result = 0

                    val currentNum = grid[pos.first][pos.second]

                    val forwardPos = pos.first + directions[directionIndex].first to pos.second + directions[directionIndex].second

                    directions[directionIndex].let {
                        grid.getOrNull(forwardPos.first)?.getOrNull(forwardPos.second)
                    }?.takeIf {
                        currentNum == 1 && it == 2 || (currentNum - it).absoluteValue == 2
                    }?.also {
                        result = dfs(forwardPos, directionIndex, turnCount)
                    }

                    if (turnCount == 1 && currentNum != 1) {
                        val turnDirectionIndex = (directionIndex + 1) % 4
                        val turnPos = pos.first + directions[turnDirectionIndex].first to pos.second + directions[turnDirectionIndex].second
                        grid.getOrNull(turnPos.first)?.getOrNull(turnPos.second)?.takeIf {
                            it != currentNum && it != 1
                        }?.also {
                            result = maxOf(result, dfs(turnPos, turnDirectionIndex, 0))
                        }
                    }

                    result++
                    cache[pos.first][pos.second][directionIndex][turnCount] = result
                    result
                }
            }

            return initPos.maxOfOrNull { pos ->
                (0 until 4).maxOf { directionIndex ->
                    maxOf(dfs(pos, directionIndex, 0), dfs(pos, directionIndex, 1))
                }
            } ?: 0
        }
    }

    expect {
        Solution().lenOfVDiagonal(
            arrayOf(
                intArrayOf(1, 1, 2, 1, 0, 1, 1, 0, 0),
                intArrayOf(1, 0, 1, 2, 2, 0, 2, 1, 1),
                intArrayOf(1, 0, 2, 0, 2, 1, 1, 1, 1),
            )
        )
    }
}
