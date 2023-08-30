package p22xx

import util.expect

fun main() {
    class Solution {
        fun findKDistantIndices(nums: IntArray, key: Int, k: Int): List<Int> {
            val result = sortedSetOf<Int>()

            nums.forEachIndexed { index, num ->
                if (num == key) {
                    result.addAll((index - k).coerceAtLeast(0)..(index + k).coerceAtMost(nums.lastIndex))
                }
            }

            return result.toList()
        }
    }

    expect {
        Solution().findKDistantIndices(
            intArrayOf(), 1, 2
        )
    }
}