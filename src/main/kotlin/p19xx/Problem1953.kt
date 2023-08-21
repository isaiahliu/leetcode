package p19xx

import util.expect

fun main() {
    class Solution {
        fun numberOfWeeks(milestones: IntArray): Long {
            var sum = 0L
            var max = 0
            milestones.forEach {
                sum += it
                max = max.coerceAtLeast(it)
            }

            val remain = sum - max

            return sum - (max - remain - 1).coerceAtLeast(0)
        }
    }

    expect {
        Solution().numberOfWeeks(
            intArrayOf(5, 2, 1)
        )
    }
}