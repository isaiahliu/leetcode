package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun buildArray(nums: IntArray): IntArray {
            return IntArray(nums.size) {
                nums[nums[it]]
            }
        }
    }

    measureTimeMillis {
        Solution().buildArray(
            intArrayOf(-1, 0, 0, 1, 2)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}