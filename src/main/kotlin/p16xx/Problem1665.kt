package p16xx

import util.expect

fun main() {
    class Solution {
        fun minimumEffort(tasks: Array<IntArray>): Int {
            var result = 0

            tasks.sortWith(compareByDescending<IntArray> { it[1] - it[0] }.thenByDescending { it[1] })

            var cur = 0
            tasks.forEach { (cost, min) ->
                if (cur < min) {
                    result += min - cur
                    cur = min
                }

                cur -= cost
            }

            return result
        }
    }

    expect {
        Solution().minimumEffort(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 4),
                intArrayOf(4, 8),
            )
        )
    }
}

