package p23xx

import util.expect

fun main() {
    class Solution {
        fun arithmeticTriplets(nums: IntArray, diff: Int): Int {
            val queue = IntArray(201)

            var result = 0
            nums.forEach { n ->
                queue[n] = (queue.getOrNull(n - diff) ?: 0) + 1

                if (queue[n] >= 3) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().arithmeticTriplets(
            intArrayOf(4, 5, 6, 7, 8, 9), 2
        )
    }
}