package p06xx

import util.expect

fun main() {
    class Solution {
        fun maxAreaOfIsland(grid: Array<IntArray>): Int {
            val lands = hashSetOf<Pair<Int, Int>>()
            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num > 0) {
                        lands.add(r to c)
                    }
                }
            }

            var result = 0
            while (lands.isNotEmpty()) {
                val first = lands.first()
                val island = hashSetOf(first)
                lands.remove(first)

                val t = hashSetOf(first)
                while (t.isNotEmpty()) {
                    t.toSet().also { t.clear() }.forEach { (r, c) ->
                        arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).forEach {
                            if (lands.remove(it)) {
                                island.add(it)
                                t.add(it)
                            }
                        }
                    }
                }

                result = result.coerceAtLeast(island.size)
            }

            return result
        }
    }

    expect {
        Solution().maxAreaOfIsland(
            arrayOf(
                intArrayOf(1, 1, 1), intArrayOf(1, 0, 1)
            )
        )
    }
}