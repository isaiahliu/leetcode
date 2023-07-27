package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun busyStudent(startTime: IntArray, endTime: IntArray, queryTime: Int): Int {
            return startTime.indices.count {
                queryTime in startTime[it]..endTime[it]
            }
        }
    }

    measureTimeMillis {
        Solution().busyStudent(
            intArrayOf(1, 2, 3), intArrayOf(3, 2, 7), 4
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

