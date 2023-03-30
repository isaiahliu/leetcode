package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxCount(m: Int, n: Int, ops: Array<IntArray>): Int {
            var tm = m
            var tn = n

            ops.forEach { (r, c) ->
                tm = tm.coerceAtMost(r)
                tn = tn.coerceAtMost(c)
            }

            return tm * tn
        }
    }

    measureTimeMillis {
        Solution().maxCount(
            1, 1, arrayOf()
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}