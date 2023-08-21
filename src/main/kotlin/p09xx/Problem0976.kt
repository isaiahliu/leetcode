package p09xx

import util.expect

fun main() {
    class Solution {
        fun largestPerimeter(nums: IntArray): Int {
            nums.sortDescending()

            for (i in 0 until nums.size - 2) {
                val l1 = nums[i]
                val l2 = nums[i + 1]
                val l3 = nums[i + 2]

                if (l1 < l2 + l3) {
                    return l1 + l2 + l3
                }
            }

            return 0
        }
    }

    expect {
        Solution().largestPerimeter(
            intArrayOf(5, 1, 3, 4, 2)
        )
    }
}
