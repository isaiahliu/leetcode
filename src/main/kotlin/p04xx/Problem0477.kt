package p04xx

import util.expect

fun main() {
    class Solution {
        fun totalHammingDistance(nums: IntArray): Int {
            var result = 0

            for (i in nums.indices) {
                for (j in i + 1 until nums.size) {
                    result += Integer.bitCount(nums[i] xor nums[j])
                }
            }

            return result
        }
    }

    expect {
        Solution().totalHammingDistance(
            intArrayOf()
        )
    }
}