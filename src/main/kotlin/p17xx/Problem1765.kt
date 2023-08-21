package p17xx

import util.expect

fun main() {
    class Solution {
        fun highestPeak(isWater: Array<IntArray>): Array<IntArray> {
            val tasks = hashSetOf<Pair<Int, Int>>()

            val result = Array(isWater.size) {
                IntArray(isWater[it].size) { -1 }
            }

            isWater.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num == 1) {
                        tasks.add(r to c)
                        result[r][c] = 0
                    }
                }
            }

            var height = 0
            while (tasks.isNotEmpty()) {
                height++

                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        result.getOrNull(it.first)?.getOrNull(it.second) == -1
                    }.forEach {
                        result[it.first][it.second] = height
                        tasks.add(it)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().highestPeak(
            arrayOf()
        )
    }
}
