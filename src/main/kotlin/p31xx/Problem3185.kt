package p31xx

import util.expect

fun main() {
    class Solution {
        fun countCompleteDayPairs(hours: IntArray): Long {
            val counts = hashMapOf<Int, Int>()

            var result = 0L
            hours.forEach {
                (it % 24).also {
                    counts[(24 - it) % 24]?.also {
                        result += it
                    }

                    counts[it] = (counts[it] ?: 0) + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().countCompleteDayPairs(
            intArrayOf()
        )
    }
}
