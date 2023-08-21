package p04xx

import util.expect

fun main() {
    class Solution {
        fun canPartition(nums: IntArray): Boolean {
            nums.sort()

            val sum = nums.sum()

            if (sum and 1 > 0) {
                return false
            }

            val cache = hashSetOf<Pair<Int, Int>>()

            fun walk(index: Int, target: Int): Boolean {
                if (target == 0) {
                    return true
                }

                if (index >= nums.size) {
                    return false
                }

                if (!cache.add(index to target)) {
                    return false
                }

                var result = false
                val num = nums[index]

                if (num <= target) {
                    result = walk(index + 1, target) || walk(index + 1, target - num)
                }

                return result
            }

            return walk(0, sum / 2)
        }
    }

    expect {
        Solution().canPartition(
            intArrayOf(1, 2, 3, 5)
        )
    }
}


