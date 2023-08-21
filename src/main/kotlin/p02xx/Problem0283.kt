package p02xx

import util.expect

fun main() {
    class Solution {
        fun moveZeroes(nums: IntArray): Unit {
            var moveCount = 0
            nums.forEachIndexed { index, i ->
                if (i == 0) {
                    moveCount++
                } else if (moveCount > 0) {
                    nums[index - moveCount] = nums[index]
                    nums[index] = 0
                }
            }
        }
    }

    expect {
        Solution().moveZeroes(
            intArrayOf()
        )
    }
}

