package p30xx

import util.expect

fun main() {
    class Solution {
        fun maxFrequencyElements(nums: IntArray): Int {
            return nums.toList().groupingBy { it }.eachCount().values.groupingBy { it }.eachCount().entries.maxBy { it.key }.let { it.key * it.value }
        }
    }

    expect {
        Solution().maxFrequencyElements(
            intArrayOf(6, 9, 10, 11, 11, 12)
        )
    }
}
