package p33xx

import util.expect

fun main() {
    class Solution {
        fun maxDistinctElements(nums: IntArray, k: Int): Int {
            nums.sort()

            var result = 0

            var current = Int.MIN_VALUE

            nums.forEach {
                if (current <= it + k) {
                    result++
                    current = maxOf(current, it - k) + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().maxDistinctElements(
            intArrayOf(4, 4, 4, 4), 1
        )
    }
}
