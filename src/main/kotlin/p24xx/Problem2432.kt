package p24xx

import util.expect

fun main() {
    class Solution {
        fun hardestWorker(n: Int, logs: Array<IntArray>): Int {
            var cur = 0
            var max = 0
            var result = Int.MAX_VALUE

            logs.forEach { (id, endTime) ->
                val cost = (endTime - cur)

                if (cost > max) {
                    max = cost
                    result = id
                } else if (cost == max) {
                    max = cost
                    result = result.coerceAtMost(id)

                }

                cur = endTime
            }
            return result
        }
    }

    expect {
        Solution().hardestWorker(
            1, arrayOf()
        )
    }
}