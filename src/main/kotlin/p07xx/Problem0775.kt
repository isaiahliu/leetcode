package p07xx

import util.expect

fun main() {
    class Solution {
        fun isIdealPermutation(nums: IntArray): Boolean {
            nums.forEachIndexed { index, i ->
                if (index - i !in -1..1) {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().isIdealPermutation(
            intArrayOf(1, 0, 2)
        )
    }
}