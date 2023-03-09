package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMaximumXOR(nums: IntArray): Int {
            var max = 0

            fun matchGroups(digit: Int, group1: List<Int>, group2: List<Int>, r: Int) {
                if (r < max) {
                    return
                }

                if (group1.isEmpty() || group2.isEmpty()) {
                    return
                }

                if (digit < 0) {
                    max = r
                    return
                }

                val base = 1 shl digit

                val g1 = group1.groupBy { it and base }
                val g2 = group2.groupBy { it and base }

                if ((g1.keys + g2.keys).size > 1) {
                    matchGroups(digit - 1, g1[0].orEmpty(), g2[base].orEmpty(), r)
                    matchGroups(digit - 1, g2[0].orEmpty(), g1[base].orEmpty(), r)
                } else {
                    matchGroups(digit - 1, g1[0].orEmpty(), g2[0].orEmpty(), r - base)
                    matchGroups(digit - 1, g2[base].orEmpty(), g1[base].orEmpty(), r - base)
                }
            }

            val numSet = nums.toList()
            matchGroups(30, numSet, numSet, Int.MAX_VALUE)

            return max
        }
    }

    measureTimeMillis {
        Solution().findMaximumXOR(
            intArrayOf(32, 18, 33, 42, 29, 20, 26, 36, 15, 46)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


