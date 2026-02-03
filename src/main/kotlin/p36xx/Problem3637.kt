package p36xx

import util.expect

fun main() {
    class Solution {
        fun isTrionic(nums: IntArray): Boolean {
            if (nums[1] <= nums[0]) {
                return false
            }

            var times = 0
            var pre = nums[0]

            for (i in 1 until nums.size) {
                val num = nums[i]
                when {
                    times > 2 -> return false

                    pre == num -> return false

                    num > pre && times % 2 == 0 -> Unit
                    num < pre && times % 2 == 1 -> Unit

                    else -> times++
                }

                pre = num
            }

            return times == 2
        }
    }

    expect {
        Solution().isTrionic(
            intArrayOf(1, 3, 5, 4, 2, 6)
        )
    }
}
