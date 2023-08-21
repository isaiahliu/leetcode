package p17xx

import util.expect

fun main() {
    class Solution {
        fun largestAltitude(gain: IntArray): Int {
            var result = 0

            gain.fold(0) { a, b ->
                (a + b).also { result = result.coerceAtLeast(it) }
            }

            return result
        }
    }

    expect {
        Solution().largestAltitude(
            intArrayOf(-5, 1, 5, 0, -7)
        )
    }
}
