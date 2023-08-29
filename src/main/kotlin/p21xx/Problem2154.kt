package p21xx

import util.expect

fun main() {
    class Solution {
        fun findFinalValue(nums: IntArray, original: Int): Int {
            val numSet = nums.toSet()

            var result = original
            while (result in numSet) {
                result *= 2
            }

            return result
        }
    }

    expect {
        Solution().findFinalValue(
            intArrayOf(), 1
        )
    }
}