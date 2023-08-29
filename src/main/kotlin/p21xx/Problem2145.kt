package p21xx

import util.expect

fun main() {
    class Solution {
        fun numberOfArrays(differences: IntArray, lower: Int, upper: Int): Int {
            var sum = 0L
            var min = 0L
            var max = 0L
            differences.forEach {
                sum += it

                min = min.coerceAtMost(sum)
                max = max.coerceAtLeast(sum)
            }

            return (upper - lower - (max - min).toInt() + 1).coerceAtLeast(0)
        }
    }

    expect {
        Solution().numberOfArrays(
            intArrayOf(-40), -46, 53
        )
    }
}