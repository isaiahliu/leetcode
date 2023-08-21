package p06xx

import util.expect

fun main() {
    class Solution {
        fun checkPossibility(nums: IntArray): Boolean {
            if (nums.size <= 2) {
                return true
            }

            var dec = false
            var max = nums[0]
            for (i in 1 until nums.size) {
                if (nums[i] < max) {
                    if (dec) {
                        return false
                    } else {
                        dec = true

                        if ((nums.getOrNull(i + 1) ?: return true) < max) {
                            if (nums[i] < (nums.getOrNull(i - 2) ?: nums[i])) {
                                return false
                            } else {
                                max = nums[i]
                            }
                        }
                    }
                } else {
                    max = nums[i]
                }
            }

            return true
        }
    }

    expect {
        Solution().checkPossibility(
            intArrayOf(5, 7, 1, 8)
        )
    }
}