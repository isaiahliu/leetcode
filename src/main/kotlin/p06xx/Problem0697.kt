package p06xx

import util.expect

fun main() {
    class Solution {
        fun findShortestSubArray(nums: IntArray): Int {
            val map = hashMapOf<Int, IntArray>()

            nums.forEachIndexed { index, num ->
                val idx = map.computeIfAbsent(num) { intArrayOf(0, index, 0) }
                idx[0]++
                idx[2] = index
            }

            return map.values.sortedWith(compareByDescending<IntArray> { it[0] }.thenBy { it[2] - it[1] }).first()
                .let { it[2] - it[1] + 1 }
        }
    }

    expect {
        Solution().findShortestSubArray(
            intArrayOf(1, 2, 2, 3, 1)
        )
    }
}