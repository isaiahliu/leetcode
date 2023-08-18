package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSpecialSubsequences(nums: IntArray): Int {
            val possibilities = intArrayOf(1, 0, 0, 0)

            nums.forEach {
                possibilities[it + 1] = ((possibilities[it + 1] * 2L + possibilities[it]) % 1000000007).toInt()
            }

            return possibilities[3]
        }
    }

    measureTimeMillis {
        Solution().countSpecialSubsequences(
            intArrayOf(0, 1, 2, 0, 1, 2)
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}