package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isIdealPermutation(nums: IntArray): Boolean {
            nums.forEachIndexed { index, i ->
                if (index - i !in -1..1) {
                    return false
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isIdealPermutation(
            intArrayOf(1, 0, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}