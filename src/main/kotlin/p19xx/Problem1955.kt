package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSpecialSubsequences(nums: IntArray): Int {
            val m = 1000000007

            val possibilities = longArrayOf(1, 0, 0, 0)

            nums.forEach {
                possibilities[it + 1] = (possibilities[it + 1] * 2 + possibilities[it]) % m
            }

            return possibilities[3].toInt()
        }
    }

    measureTimeMillis {
        Solution().countSpecialSubsequences(
            intArrayOf(0, 1, 2, 0, 1, 2)
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}