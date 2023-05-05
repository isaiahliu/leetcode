package p24xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().hardestWorker(
            1, arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}