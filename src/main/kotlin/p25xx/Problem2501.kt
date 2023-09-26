package p25xx

import util.expect

fun main() {
    class Solution {
        fun longestSquareStreak(nums: IntArray): Int {
            nums.sortDescending()
            val map = hashMapOf<Int, Int>()

            nums.forEach {
                map[it] = (map[it * it] ?: 0) + 1
            }

            return map.values.max().takeIf { it > 1 } ?: -1
        }
    }

    expect {
        Solution().longestSquareStreak(
            intArrayOf()
        )
    }
}