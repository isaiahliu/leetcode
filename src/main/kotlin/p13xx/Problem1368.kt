package p13xx

import util.expect

fun main() {
    class Solution {
        fun minCost(grid: Array<IntArray>): Int {
            val m = grid.size
            val n = grid[0].size

            val costs = Array(grid.size) {
                IntArray(grid[it].size) { Int.MAX_VALUE }
            }

            costs[0][0] = 0

            var cost = 0
            val tasks = hashSetOf(0 to 0)
            val nexts = hashSetOf<Pair<Int, Int>>()
            while (true) {
                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                        if (r == m - 1 && c == n - 1) {
                            return cost
                        }

                        if (costs[r][c] == cost) {
                            var moveR = r
                            var moveC = c
                            when (grid[r][c]) {
                                1 -> {
                                    moveC++
                                }

                                2 -> {
                                    moveC--
                                }

                                3 -> {
                                    moveR++
                                }

                                4 -> {
                                    moveR--
                                }
                            }

                            costs.getOrNull(moveR)?.getOrNull(moveC)?.takeIf { it > cost }?.also {
                                costs[moveR][moveC] = cost
                                tasks.add(moveR to moveC)
                            }

                            arrayOf(r to c + 1, r to c - 1, r + 1 to c, r - 1 to c).filter {
                                it.first in 0 until m && it.second in 0 until n
                            }.forEach {
                                if (costs[it.first][it.second] > cost + 1) {
                                    nexts.add(it)
                                    costs[it.first][it.second] = cost + 1
                                }
                            }
                        }
                    }
                }

                tasks.addAll(nexts)
                nexts.clear()
                cost++
            }
        }
    }

    expect {
        Solution().minCost(
            arrayOf(
                intArrayOf(1, 1, 1, 1),
                intArrayOf(2, 2, 2, 2),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(2, 2, 2, 2),
            )
        )
    }
}

