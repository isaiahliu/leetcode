package p10xx

import util.expect

fun main() {
    class Solution {
        fun heightChecker(heights: IntArray): Int {
            val sorted = heights.sorted()

            return heights.foldIndexed(0) { index, sum, h ->
                sum + if (h == sorted[index]) 0 else 1
            }
        }
    }

    expect {
        Solution().heightChecker(
            intArrayOf(2, 7, 4, 1, 8, 1)
        )
    }
}