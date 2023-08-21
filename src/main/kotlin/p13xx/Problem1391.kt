package p13xx

import util.expect

fun main() {
    class Solution {
        fun hasValidPath(grid: Array<IntArray>): Boolean {
            val TOP = 0
            val RIGHT = 1
            val BOTTOM = 2
            val LEFT = 3
            val visited = hashSetOf<Pair<Int, Int>>()
            val tasks = hashMapOf(0 to 0 to -1)
            val m = grid.lastIndex
            val n = grid[0].lastIndex

            while (tasks.isNotEmpty()) {
                tasks.toMap().also { tasks.clear() }.forEach { (pos, from) ->
                    val (r, c) = pos

                    val neighbors = hashMapOf<Int, Pair<Int, Int>>()
                    when (grid[r][c]) {
                        1 -> {
                            neighbors[LEFT] = r to c - 1
                            neighbors[RIGHT] = r to c + 1
                        }

                        2 -> {
                            neighbors[TOP] = r - 1 to c
                            neighbors[BOTTOM] = r + 1 to c
                        }

                        3 -> {
                            neighbors[BOTTOM] = r + 1 to c
                            neighbors[LEFT] = r to c - 1
                        }

                        4 -> {
                            neighbors[BOTTOM] = r + 1 to c
                            neighbors[RIGHT] = r to c + 1
                        }

                        5 -> {
                            neighbors[TOP] = r - 1 to c
                            neighbors[LEFT] = r to c - 1
                        }

                        6 -> {
                            neighbors[TOP] = r - 1 to c
                            neighbors[RIGHT] = r to c + 1
                        }
                    }

                    neighbors.remove(from)

                    if ((from < 0 || neighbors.size == 1) && visited.add(pos)) {
                        if (r == m && c == n) {
                            return true
                        }

                        neighbors.forEach { (nf, npos) ->
                            if (npos.first in 0..m && npos.second in 0..n) {
                                tasks[npos] = (nf + 2) % 4
                            }
                        }
                    }
                }
            }

            return false
        }
    }

    expect {
        Solution().hasValidPath(
            arrayOf(
                intArrayOf(4, 1, 3),
                intArrayOf(6, 1, 2),
            )
        )
    }
}

