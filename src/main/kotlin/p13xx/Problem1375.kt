package p13xx

import util.expect

fun main() {
    class Solution {
        fun numTimesAllBlue(flips: IntArray): Int {
            var max = 0
            var result = 0

            flips.forEachIndexed { index, num ->
                max = max.coerceAtLeast(num)
                result += (index + 1) / max
            }

            return result
        }
    }

    expect {
        Solution().numTimesAllBlue(
            intArrayOf(3, 2, 4, 1, 5)
        )
    }
}

