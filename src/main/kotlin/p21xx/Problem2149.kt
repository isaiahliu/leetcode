package p21xx

import util.expect

fun main() {
    class Solution {
        fun rearrangeArray(nums: IntArray): IntArray {
            val result = IntArray(nums.size)
            var posIndex = 0
            var negIndex = 1

            nums.forEach {
                if (it > 0) {
                    result[posIndex] = it
                    posIndex += 2
                } else {
                    result[negIndex] = it
                    negIndex += 2
                }
            }

            return result
        }
    }

    expect {
        Solution().rearrangeArray(
            intArrayOf()
        )
    }
}