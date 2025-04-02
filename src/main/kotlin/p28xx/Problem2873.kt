package p28xx

import util.expect

fun main() {
    class Solution {
        fun maximumTripletValue(nums: IntArray): Long {
            var result = 0L

            var maxNum = nums[0]
            var maxDiff = 0L
            nums.forEach {
                result = maxOf(result, maxDiff * it)
                maxDiff = maxOf(maxDiff, maxNum - it.toLong())
                maxNum = maxOf(maxNum, it)
            }

            return result
        }
    }

    expect {
        Solution().maximumTripletValue(
            intArrayOf()
        )
    }
}
