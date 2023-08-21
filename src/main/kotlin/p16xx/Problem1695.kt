package p16xx

import util.expect

fun main() {
    class Solution {
        fun maximumUniqueSubarray(nums: IntArray): Int {
            var sum = 0
            val sums = IntArray(nums.size) {
                sum += nums[it]
                sum
            }

            var left = -1
            val map = hashMapOf<Int, Int>()
            var result = 0
            nums.forEachIndexed { index, num ->
                map[num]?.also {
                    left = left.coerceAtLeast(it)
                }

                result = result.coerceAtLeast(sums[index] - sums.getOrElse(left) { 0 })

                map[num] = index
            }

            return result
        }
    }

    expect {
        Solution().maximumUniqueSubarray(
            intArrayOf()
        )
    }
}
