package p31xx

import util.expect

fun main() {
    class Solution {
        fun countAlternatingSubarrays(nums: IntArray): Long {
            var result = 0L
            var size = 0
            var last = -1

            (nums + nums.last()).forEach {
                if (it == last) {
                    result += (1 + size).toLong() * size / 2
                    size = 1
                } else {
                    size++
                }

                last = it
            }

            return result
        }
    }

    expect {
        Solution().countAlternatingSubarrays(
            intArrayOf(0, 1, 1, 1)
        )
    }
}
