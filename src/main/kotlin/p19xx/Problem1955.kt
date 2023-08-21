package p19xx

import util.expect

fun main() {
    class Solution {
        fun countSpecialSubsequences(nums: IntArray): Int {
            val possibilities = intArrayOf(1, 0, 0, 0)

            nums.forEach {
                possibilities[it + 1] = ((possibilities[it + 1] * 2L + possibilities[it]) % 1000000007).toInt()
            }

            return possibilities[3]
        }
    }

    expect {
        Solution().countSpecialSubsequences(
            intArrayOf(0, 1, 2, 0, 1, 2)
        )
    }
}