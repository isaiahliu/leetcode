package p12xx

import util.expect

fun main() {
    class Solution {
        fun longestSubsequence(arr: IntArray, difference: Int): Int {
            val map = hashMapOf<Int, Int>()

            var result = 0
            arr.forEach { num ->
                ((map[num - difference] ?: 0) + 1).also {
                    result = result.coerceAtLeast(it)
                    map[num] = it
                }
            }

            return result
        }
    }

    expect {
        Solution().longestSubsequence(
            intArrayOf(5, 3, 1), -2
        )
    }
}
