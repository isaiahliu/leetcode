package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minWastedSpace(packages: IntArray, boxes: Array<IntArray>): Int {
            val sums = TreeMap<Int, LongArray>()

            packages.forEach { num ->
                sums.computeIfAbsent(num) { longArrayOf(0, 0) }.also {
                    it[0]++
                    it[1] += num.toLong()
                }
            }

            var cur = longArrayOf(0, 0)
            sums.forEach { (_, sum) ->
                sum[0] += cur[0]
                sum[1] += cur[1]

                cur = sum
            }

            var result = Long.MAX_VALUE
            boxes.forEach {
                it.sort()

                var waste = 0L
                var lastUsed = longArrayOf(0, 0)

                it.forEach { box ->
                    sums.floorEntry(box)?.takeIf { it.value[0] > lastUsed[0] }?.also {
                        waste += box.toLong() * (it.value[0] - lastUsed[0]) - (it.value[1] - lastUsed[1])

                        lastUsed = it.value
                    }
                }

                if (lastUsed[0].toInt() == packages.size) {
                    result = result.coerceAtMost(waste)
                }
            }

            return result.takeIf { it < Long.MAX_VALUE }?.let { it % 1000000007 }?.toInt() ?: -1
        }
    }

    expect {
        Solution().minWastedSpace(
            intArrayOf(), arrayOf()
        )
    }
}
