package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mostFrequentEven(nums: IntArray): Int {
            return nums.filter { it % 2 == 0 }.groupingBy { it }.eachCount()
                .minWithOrNull(compareByDescending<Map.Entry<Int, Int>> { it.value }.thenBy { it.key })?.key ?: -1
        }
    }

    measureTimeMillis {
        Solution().mostFrequentEven(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}