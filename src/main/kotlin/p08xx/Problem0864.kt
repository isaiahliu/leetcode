package p08xx

import util.expect

fun main() {
    class Solution {
        fun shortestPathAllKeys(grid: Array<String>): Int {
            fun Int.pickKey(key: Char): Int {
                return this or (1 shl (key - 'a'))
            }

            fun Int.openLock(lock: Char): Int? {
                return if (this and (1 shl (lock - 'A')) > 0) {
                    this or (1 shl (lock - 'A' + 6))
                } else {
                    null
                }
            }

            var allKeys = 0

            val visited = hashSetOf<Pair<Pair<Int, Int>, Int>>()
            val tasks = hashSetOf<Pair<Pair<Int, Int>, Int>>()
            loop@ for ((r, row) in grid.withIndex()) {
                for ((c, p) in row.withIndex()) {
                    when (p) {
                        '@' -> {
                            visited.add(r to c to 0)
                            tasks.add(r to c to 0)
                        }

                        in 'a'..'f' -> {
                            allKeys = allKeys or (1 shl (p - 'a'))
                        }
                    }
                }
            }

            fun Int.done(): Boolean {
                return this or allKeys == this
            }

            var result = 0
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (pos, status) ->
                    if (status.done()) {
                        return result
                    }
                    val (r, c) = pos

                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        it.first in grid.indices && it.second in grid[0].indices
                    }.forEach {
                        var newStatus = status
                        when (val p = grid[it.first][it.second]) {
                            in 'a'..'f' -> {
                                newStatus = status.pickKey(p)
                            }

                            in 'A'..'F' -> {
                                newStatus = status.openLock(p) ?: return@forEach
                            }

                            '#' -> {
                                return@forEach
                            }
                        }

                        if (visited.add(it to newStatus)) {
                            tasks.add(it to newStatus)
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    expect {
        Solution().shortestPathAllKeys(
            arrayOf(
                "@Aa"
            )
        )

    }
}