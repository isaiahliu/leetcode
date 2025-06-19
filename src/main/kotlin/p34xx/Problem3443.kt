package p34xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maxDistance(s: String, k: Int): Int {
            var result = 0
            var r = 0
            var c = 0
            for ((index, ch) in s.withIndex()) {
                when (ch) {
                    'N' -> r++
                    'E' -> c++
                    'W' -> c--
                    'S' -> r--
                }

                result = maxOf(result, minOf(index + 1, r.absoluteValue + c.absoluteValue + k * 2))
            }

            return result
        }
    }

    expect {
        Solution().maxDistance(
            "", 1
        )
    }
}
