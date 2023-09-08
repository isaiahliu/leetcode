package p23xx

import util.expect

fun main() {
    class Solution {
        fun countBadPairs(nums: IntArray): Long {
            val counts = hashMapOf<Int, Int>()

            var result = 0L
            nums.forEachIndexed { index, num ->
                val diff = num - index

                result += index - (counts[diff] ?: 0)

                counts[diff] = (counts[diff] ?: 0) + 1
            }

            return result
        }
    }

    expect {
        Solution().countBadPairs(
            intArrayOf(4, 1, 3, 3)
        )
    }
}