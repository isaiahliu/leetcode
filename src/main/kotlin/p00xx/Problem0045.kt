package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun jump(nums: IntArray): Int {
            var steps = 0
            var startIndex = 0
            var endIndex = 0

            while (endIndex < nums.size - 1) {
                val nextStart = endIndex + 1
                for (i in startIndex..endIndex) {
                    endIndex = endIndex.coerceAtLeast(i + nums[i])
                }

                if (endIndex < nextStart) {
                    return 0
                }

                startIndex = nextStart
                steps++
            }

            return steps
        }
    }

    measureTimeMillis {
        println(Solution().jump(intArrayOf(1)))
    }.also { println("Time cost: ${it}ms") }
}


