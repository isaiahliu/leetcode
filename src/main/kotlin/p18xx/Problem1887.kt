package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reductionOperations(nums: IntArray): Int {
            var result = 0
            var c = 0
            nums.toList().groupingBy { it }.eachCount().entries.sortedBy { it.key }.forEach { (num, count) ->
                result += count * c
                c++
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().reductionOperations(
            intArrayOf(1, 1, 2, 2, 3)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
