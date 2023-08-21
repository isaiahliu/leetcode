package p19xx

import util.expect

fun main() {
    class Solution {
        fun countSubIslands(grid1: Array<IntArray>, grid2: Array<IntArray>): Int {
            val lands = hashSetOf<Pair<Int, Int>>()

            grid2.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num > 0) {
                        lands.add(r to c)
                    }
                }
            }

            var result = 0
            while (lands.isNotEmpty()) {
                val first = lands.first()
                lands.remove(first)
                val tasks = hashSetOf(first)

                var success = true
                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                        success = success and (grid1[r][c] == 1)

                        arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).forEach {
                            if (lands.remove(it)) {
                                tasks.add(it)
                            }
                        }
                    }
                }

                if (success) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().countSubIslands(
            arrayOf(), arrayOf()
        )
    }
}
