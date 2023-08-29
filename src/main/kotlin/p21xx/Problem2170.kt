package p21xx

import util.expect

fun main() {
    class Solution {
        fun minimumOperations(nums: IntArray): Int {
            if (nums.size <= 1) {
                return 0
            }

            val even = (nums.indices step 2).groupingBy { nums[it] }.eachCount().entries.sortedByDescending { it.value }
            val odd =
                (1 until nums.size step 2).groupingBy { nums[it] }.eachCount().entries.sortedByDescending { it.value }

            return if (even[0].key != odd[0].key) {
                nums.size - even[0].value - odd[0].value
            } else {
                nums.size - (even[0].value + (odd.getOrNull(1)?.value
                    ?: 0)).coerceAtLeast(odd[0].value + (even.getOrNull(1)?.value ?: 0))
            }
        }
    }

    expect {
        Solution().minimumOperations(
            intArrayOf()
        )
    }
}