package p05xx

import util.expect

fun main() {
    class Solution {
        fun findMaxLength(nums: IntArray): Int {
            val map = hashMapOf(0 to -1)
            var result = 0

            var sum = 0
            nums.forEachIndexed { index, num ->
                sum += if (num == 1) 1 else -1

                map[sum]?.also {
                    result = result.coerceAtLeast(index - it)
                } ?: run { map[sum] = index }
            }

            return result
        }
    }

    expect {
        Solution().findMaxLength(intArrayOf(0, 1, 0))
    }
}