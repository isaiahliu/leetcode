package p31xx

import util.expect

fun main() {
    class Solution {
        fun countCompleteDayPairs(hours: IntArray): Int {
            val counts = hashMapOf<Int, Int>()

            var result = 0
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
