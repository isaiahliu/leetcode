package p05xx

import util.expect

fun main() {
    class Solution {
        fun findLHS(nums: IntArray): Int {
            val entries = nums.toList().groupingBy { it }.eachCount().entries.sortedBy { it.key }

            var result = 0
            for (i in 1 until entries.size) {
                if (entries[i].key - entries[i - 1].key == 1) {
                    result = result.coerceAtLeast(entries[i].value + entries[i - 1].value)
                }
            }

            return result
        }
    }

    expect {
        Solution().findLHS(
            intArrayOf()
        )

    }
}