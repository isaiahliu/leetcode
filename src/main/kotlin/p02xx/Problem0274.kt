package p02xx

import util.expect

fun main() {
    class Solution {
        fun hIndex(citations: IntArray): Int {
            val counts = IntArray(1001)

            var max = 0
            citations.forEach {
                max = max.coerceAtLeast(it)
                counts[it]++
            }

            for (i in max downTo 0) {
                counts.getOrNull(i + 1)?.also {
                    counts[i] += it
                }

                if (counts[i] >= i) {
                    return i
                }
            }

            return 0
        }
    }

    expect {
        Solution().hIndex(
            intArrayOf(3, 0, 6, 1, 5)
        )
    }
}

