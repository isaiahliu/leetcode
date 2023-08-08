package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countStudents(students: IntArray, sandwiches: IntArray): Int {
            val remain = students.count { it == 0 }.let { intArrayOf(it, students.size - it) }

            for (sandwich in sandwiches) {
                if (remain[sandwich] > 0) {
                    remain[sandwich]--
                } else {
                    break
                }
            }

            return remain[0] + remain[1]
        }
    }

    measureTimeMillis {
        Solution().countStudents(
            intArrayOf(1, 1, 0, 0), intArrayOf(0, 1, 0, 1)
        ).also { println("${it} should be ${it}") }
    }.also { println("Time cost: ${it}ms") }
}
