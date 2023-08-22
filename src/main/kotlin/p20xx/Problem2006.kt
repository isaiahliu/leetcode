package p20xx

import util.expect

fun main() {
    class Solution {
        fun countKDifference(nums: IntArray, k: Int): Int {
            var result = 0

            val map = hashMapOf<Int, Int>()

            nums.forEach {
                map[it + k]?.also { result += it }
                map[it - k]?.also { result += it }

                map[it] = (map[it] ?: 0) + 1
            }

            return result
        }
    }

    expect {
        Solution().countKDifference(
            intArrayOf(1, 2, 2, 1), 1
        )
    }
}