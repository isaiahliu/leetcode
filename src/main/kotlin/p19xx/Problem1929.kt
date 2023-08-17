package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getConcatenation(nums: IntArray): IntArray {
            return nums + nums
        }
    }

    measureTimeMillis {
        Solution().getConcatenation(
            intArrayOf(1, 2, 1)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}