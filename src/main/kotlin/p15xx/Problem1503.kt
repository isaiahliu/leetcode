package p15xx

import util.expect

fun main() {
    class Solution {
        fun getLastMoment(n: Int, left: IntArray, right: IntArray): Int {
            var result = 0

            left.maxOrNull()?.also {
                result = result.coerceAtLeast(it)
            }

            right.minOrNull()?.also {
                result = result.coerceAtLeast(n - it)
            }

            return result
        }
    }

    expect {
        Solution().getLastMoment(
            7, intArrayOf(0, 1, 2, 3, 4, 5, 6, 7), intArrayOf()
        )
    }
}

