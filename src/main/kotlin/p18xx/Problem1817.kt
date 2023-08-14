package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findingUsersActiveMinutes(logs: Array<IntArray>, k: Int): IntArray {
            val result = IntArray(k)

            logs.groupBy { it[0] }.values.forEach {
                it.map { it[1] }.distinct().size.takeIf { it > 0 }?.also {
                    result[it - 1]++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findingUsersActiveMinutes(
            arrayOf(), 4
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
