package p10xx

import util.expect

fun main() {
    class Solution {
        fun shipWithinDays(weights: IntArray, days: Int): Int {
            fun binarySearch(min: Int, max: Int): Int {
                if (min > max) {
                    return Int.MAX_VALUE
                }

                val mid = (min + max) / 2

                var d = 1
                var sum = 0
                weights.forEach {
                    sum += it

                    if (sum > mid) {
                        d++
                        sum = it
                    }
                }

                return if (d > days) {
                    binarySearch(mid + 1, max)
                } else {
                    binarySearch(min, mid - 1).coerceAtMost(mid)
                }
            }

            return binarySearch(weights.max(), weights.sum())
        }
    }

    expect {
        Solution().shipWithinDays(
            intArrayOf(), 1
        )
    }
}
