package p14xx

import util.expect

fun main() {
    class Solution {
        fun average(salary: IntArray): Double {
            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE

            var sum = 0

            salary.forEach {
                sum += it
                min = min.coerceAtMost(it)
                max = max.coerceAtLeast(it)
            }

            return (sum - min - max).toDouble() / (salary.size - 2)
        }
    }

    expect {
        Solution().average(
            intArrayOf()
        )

    }
}

