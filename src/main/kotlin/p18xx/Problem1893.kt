package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isCovered(ranges: Array<IntArray>, left: Int, right: Int): Boolean {
            val remain = (left..right).toMutableSet()

            ranges.forEach {
                remain.removeAll(it[0]..it[1])

                if (remain.isEmpty()) {
                    return true
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().isCovered(
            arrayOf(), 1, 2
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
