package p12xx

import kotlin.math.sign
import util.expect

fun main() {
    class Solution {
        fun smallestDivisor(nums: IntArray, threshold: Int): Int {
            nums.sortDescending()

            fun binarySearch(start: Int, end: Int): Int {
                if (start > end) {
                    return Int.MAX_VALUE
                }

                val mid = (start + end) / 2

                var sum = 0

                for (num in nums) {
                    sum += num / mid + (num % mid).sign

                    if (sum > threshold) {
                        return binarySearch(mid + 1, end)
                    }
                }

                return mid.coerceAtMost(binarySearch(start, mid - 1))
            }

            return binarySearch(1, nums[0])
        }
    }

    expect {
        Solution().smallestDivisor(
            intArrayOf(30, 50), 10000
        )
    }
}
