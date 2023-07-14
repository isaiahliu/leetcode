package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestSubsequence(arr: IntArray, difference: Int): Int {
            val map = hashMapOf<Int, Int>()

            var result = 0
            arr.forEach { num ->
                ((map[num - difference] ?: 0) + 1).also {
                    result = result.coerceAtLeast(it)
                    map[num] = it
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestSubsequence(
            intArrayOf(5, 3, 1), -2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
