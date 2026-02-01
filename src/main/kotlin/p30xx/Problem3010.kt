package p30xx

import util.expect

fun main() {
    class Solution {
        fun minimumCost(nums: IntArray): Int {
            var num2 = Int.MAX_VALUE
            var num3 = Int.MAX_VALUE

            for (i in 1 until nums.size) {
                val num = nums[i]
                when {
                    num < num2 -> {
                        num3 = num2
                        num2 = num
                    }

                    num < num3 -> {
                        num3 = num
                    }
                }
            }

            return nums[0] + num2 + num3
        }
    }

    expect {
        Solution().minimumCost(
            intArrayOf()
        )
    }
}
