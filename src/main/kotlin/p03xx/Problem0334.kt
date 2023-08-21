package p03xx

import util.expect

fun main() {
    class Solution {
        fun increasingTriplet(nums: IntArray): Boolean {
            if (nums.size < 3) {
                return false
            }

            var pre = nums[0]
            var index = 1

            var first = pre
            var second = Int.MAX_VALUE
            while (index < nums.size) {
                val num = nums[index++]
                if (num > pre) {
                    if (num > second) {
                        return true
                    } else {
                        first = pre
                        second = num
                    }
                } else {
                    if (num in first + 1 until second) {
                        second = num
                    }
                }

                pre = num
            }

            return false
        }
    }

    expect {
        Solution().increasingTriplet(
            intArrayOf(1, 5, 0, 4, 1, 3)
        )
    }
}

