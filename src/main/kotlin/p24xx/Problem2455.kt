package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun averageValue(nums: IntArray): Int {
            return nums.filter { it % 6 == 0 }.takeIf { it.isNotEmpty() }?.let {
                it.sum() / it.size
            } ?: 0
        }
    }

    measureTimeMillis {
        Solution().averageValue(
            intArrayOf(1, 2, 5)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}
