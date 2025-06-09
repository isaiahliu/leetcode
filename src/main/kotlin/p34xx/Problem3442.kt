package p34xx

import util.expect

fun main() {
    class Solution {
        fun maxDifference(s: String): Int {
            val counts = IntArray(26)
            s.forEach { counts[it - 'a']++ }

            var maxOdd = 0
            var minEven = s.length

            counts.forEach {
                when {
                    it == 0 -> {}
                    it % 2 == 0 -> minEven = minOf(minEven, it)
                    it % 2 == 1 -> maxOdd = maxOf(maxOdd, it)
                }
            }

            return maxOdd - minEven
        }
    }

    expect {
        Solution().maxDifference(
            "bif"
        )
    }
}
