package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canJump(nums: IntArray): Boolean {
            var startIndex = 0
            var endIndex = 0

            while (endIndex < nums.lastIndex) {
                val nextStart = endIndex + 1
                for (i in startIndex..endIndex) {
                    endIndex = endIndex.coerceAtLeast(i + nums[i])
                }

                if (endIndex < nextStart) {
                    return false
                }

                startIndex = nextStart
            }

            return true
        }
    }

    measureTimeMillis {
        println(Solution().canJump(intArrayOf(1, 2, 3, 4)))
    }.also { println("Time cost: ${it}ms") }
}


