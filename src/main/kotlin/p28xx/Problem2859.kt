package p28xx

import util.expect

fun main() {
    class Solution {
        fun sumIndicesWithKSetBits(nums: List<Int>, k: Int): Int {
            return nums.indices.filter { it.countOneBits() == k }.sumOf { nums[it] }
        }
    }

    expect {
        Solution().sumIndicesWithKSetBits(
            listOf(), 1
        )
    }
}
