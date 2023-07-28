package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findLeastNumOfUniqueInts(arr: IntArray, k: Int): Int {
            var t = k
            var result = 0
            val counts = arr.toList().groupingBy { it }.eachCount().values.sorted()

            for (count in counts) {
                if (t >= count) {
                    t -= count
                    result++
                } else {
                    break
                }
            }

            return counts.size - result
        }
    }

    measureTimeMillis {
        Solution().findLeastNumOfUniqueInts(
            intArrayOf(5, 5, 4), 1
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

