package p13xx

import util.expect

fun main() {
    class Solution {
        fun maxValueAfterReverse(nums: IntArray): Int {
            fun diff(num1: Int, num2: Int): Int {
                return if (num1 < num2) num2 - num1 else num1 - num2
            }

            val last = nums[nums.lastIndex]

            var sum = diff(nums[0], nums.getOrNull(1) ?: return 0)
            var edgeMax = 0
            var pairMin = nums[0].coerceAtLeast(nums[1])
            var pairMax = nums[0].coerceAtMost(nums[1])
            for (i in 1 until nums.lastIndex) {
                sum += diff(nums[i], nums[i + 1])

                edgeMax = edgeMax.coerceAtLeast(diff(nums[0], nums[i + 1]) - diff(nums[i], nums[i + 1]))
                edgeMax = edgeMax.coerceAtLeast(diff(nums[i - 1], last) - diff(nums[i - 1], nums[i]))

                pairMin = pairMin.coerceAtMost(nums[i].coerceAtLeast(nums[i + 1]))
                pairMax = pairMax.coerceAtLeast(nums[i].coerceAtMost(nums[i + 1]))
            }

            return sum + edgeMax.coerceAtLeast((pairMax - pairMin) * 2)
        }
    }

    expect {
        Solution().maxValueAfterReverse(
            intArrayOf(5, -7, 9, -6, 8)
        )
    }
}
