package p11xx

import util.expect

fun main() {
    class Solution {
        fun maxDistance(grid: Array<IntArray>): Int {
            val visited = hashSetOf<Pair<Int, Int>>()

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, n ->
                    if (n == 1) {
                        visited.add(r to c)
                    }
                }
            }

            val tasks = visited.toMutableSet()

            var distance = -1
            while (tasks.isNotEmpty()) {
                distance++

                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        it.first in grid.indices && it.second in grid[0].indices
                    }.forEach {
                        if (visited.add(it)) {
                            tasks.add(it)
                        }
                    }
                }
            }

            return distance.takeIf { it > 0 } ?: -1
        }
    }

    expect {
        Solution().maxDistance(
            arrayOf()
        )
    }
}