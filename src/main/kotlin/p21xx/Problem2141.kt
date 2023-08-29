package p21xx

import util.expect

fun main() {
    class Solution {
        fun maxRunTime(n: Int, batteries: IntArray): Long {
            batteries.sortDescending()

            var sum = batteries.fold(0L) { a, b -> a + b }
            var remain = n

            var index = 0

            while (remain > 1) {
                var first = batteries[index]

                if (first > (sum - first) / (remain - 1)) {
                    index++
                    sum -= first
                    remain--
                } else {
                    return sum / remain
                }
            }

            return sum
        }
    }

    expect {
        Solution().maxRunTime(
            2, intArrayOf(
                3, 3, 3
            )
        )
    }
}