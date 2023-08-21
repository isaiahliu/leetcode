package p00xx

import util.expect

fun main() {
    class Solution {
        fun removeDuplicates(nums: IntArray): Int {
            var previousNum = nums[0]
            var previousNumCount = 1
            var writeIndex = 1

            for (i in 1 until nums.size) {
                val num = nums[i]

                if (num == previousNum) {
                    previousNumCount++
                } else {
                    previousNum = num
                    previousNumCount = 1
                }

                if (previousNumCount <= 2) {
                    nums[writeIndex++] = num
                }
            }

            return writeIndex
        }
    }

    expect {
        Solution().removeDuplicates(intArrayOf())
    }
}

