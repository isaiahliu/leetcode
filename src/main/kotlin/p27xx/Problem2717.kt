package p27xx

import util.expect

fun main() {
    class Solution {
        fun semiOrderedPermutation(nums: IntArray): Int {
            var oneIndex = 0
            var nIndex = 0

            nums.forEachIndexed { index, num ->
                when (num) {
                    1 -> oneIndex = index
                    nums.size -> nIndex = index
                }
            }

            return oneIndex + nums.lastIndex - nIndex - if (oneIndex > nIndex) 1 else 0
        }
    }

    expect {
        Solution().semiOrderedPermutation(
            intArrayOf()
        )
    }
}
