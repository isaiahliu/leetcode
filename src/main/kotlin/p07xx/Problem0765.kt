package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSwapsCouples(row: IntArray): Int {
            var result = 0

            val pos = row.mapIndexed { index, i -> i to index }.toMap().toMutableMap()

            var last = row.lastIndex

            while (last > 0) {
                if (row[last] / 2 * 2 != row[last - 1] / 2 * 2) {
                    val targetPos = pos[row[last] / 2 * 2 + 1 - row[last] % 2] ?: 0

                    val targetPos2 = targetPos / 2 * 2 + 1 - targetPos % 2

                    var switchIndex = last - 1
                    var switchIndex2 = targetPos
                    if (row[last - 1] / 2 * 2 != row[targetPos2] / 2 * 2) {
                        switchIndex = last
                        switchIndex2 = pos[row[last - 1] / 2 * 2 + 1 - row[last - 1] % 2] ?: 0
                    }

                    pos[row[switchIndex]] = switchIndex2
                    pos[row[switchIndex2]] = switchIndex

                    val t = row[switchIndex]
                    row[switchIndex] = row[switchIndex2]
                    row[switchIndex2] = t

                    result++
                }

                last -= 2
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minSwapsCouples(
            intArrayOf(0, 2, 1, 3)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}