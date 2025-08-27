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

                    IntArray(8)
                }
            }

            fun dfs(pos: Pair<Int, Int>, directionIndex: Int, turnCount: Int): Int {
                return if (cache[pos.first][pos.second][directionIndex * 2 + turnCount] > 0) {
                    cache[pos.first][pos.second][directionIndex * 2 + turnCount]
                } else {
                    val currentNum = grid[pos.first][pos.second]

                    val result = (0..turnCount).maxOf { diffDirectionIndex ->
                        val newDirectionIndex = (directionIndex + diffDirectionIndex) % 4

                        val newPos = pos.first + directions[newDirectionIndex].first to pos.second + directions[newDirectionIndex].second

                        grid.getOrNull(newPos.first)?.getOrNull(newPos.second)?.takeIf {
                            (currentNum - it).absoluteValue == 2 || diffDirectionIndex + currentNum == 1 && it == 2
                        }?.let {
                            dfs(newPos, newDirectionIndex, diffDirectionIndex xor turnCount)
                        } ?: 0
                    } + 1

                    cache[pos.first][pos.second][directionIndex * 2 + turnCount] = result
                    result
                }
            }

            return initPos.maxOfOrNull { pos ->
                (0 until 8).maxOf {
                    dfs(pos, it / 2, it % 2)
                }
            } ?: 0
        }
    }

    expect {
        Solution().lenOfVDiagonal(
            arrayOf(
                intArrayOf(1),
            )
        )
    }
}
