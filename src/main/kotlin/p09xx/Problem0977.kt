package p09xx

import util.expect

fun main() {
    class Solution {
        fun sortedSquares(nums: IntArray): IntArray {
            var l = -1
            var r = 0

            while (r < nums.size && nums[r] < 0) {
                l++
                r++
            }

            return IntArray(nums.size) {
                when {
                    l < 0 -> {
                        nums[r] * nums[r++]
                    }

                    r == nums.size -> {
                        nums[l] * nums[l--]
                    }

                    -nums[l] < nums[r] -> {
                        nums[l] * nums[l--]
                    }

                    else -> {
                        nums[r] * nums[r++]
                    }
                }
            }
        }
    }

    expect {
        Solution().sortedSquares(
            intArrayOf(-3, -2, 0, 1, 2)
        )
    }
}
