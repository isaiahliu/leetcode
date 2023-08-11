package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumSize(nums: IntArray, maxOperations: Int): Int {
            nums.sortDescending()

            fun binarySearch(start: Int, end: Int): Int {
                if (start > end) {
                    return Int.MAX_VALUE
                }

                val mid = (start + end) / 2
                var success = true

                var count = 0
                for (num in nums) {
                    if (num <= mid) {
                        break
                    }

                    count += num / mid
                    if (num % mid == 0) {
                        count--
                    }

                    if (count > maxOperations) {
                        success = false
                        break
                    }
                }

                return if (success) {
                    mid.coerceAtMost(binarySearch(start, mid - 1))
                } else {
                    binarySearch(mid + 1, end)
                }
            }

            return binarySearch(1, nums[0])
        }
    }

    measureTimeMillis {
        Solution().minimumSize(
            intArrayOf(), 1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
