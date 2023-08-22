package p20xx

import util.expect

fun main() {
    class Solution {
        fun sumOfBeauties(nums: IntArray): Int {
            val minNums = IntArray(nums.size) { nums[it] }
            val maxNums = IntArray(nums.size) { nums[it] }

            for (i in 1 until nums.size) {
                minNums[nums.lastIndex - i] = minNums[nums.lastIndex - i].coerceAtMost(minNums[nums.lastIndex - i + 1])
                maxNums[i] = maxNums[i].coerceAtLeast(maxNums[i - 1])
            }

            var result = 0
            for (i in 1 until nums.lastIndex) {
                result += when {
                    nums[i] in maxNums[i - 1] + 1 until minNums[i + 1] -> 2
                    nums[i] in nums[i - 1] + 1 until nums[i + 1] -> 1
                    else -> 0
                }
            }

            return result
        }
    }

    expect {
        Solution().sumOfBeauties(
            intArrayOf(1, 2, 3)
        )
    }
}