package p17xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countStudents(students: IntArray, sandwiches: IntArray): Int {
            val studentsQueue = LinkedList(students.toList())
            val sandwichQueue = LinkedList(sandwiches.toList())

            var failCount = 0

            while (failCount < studentsQueue.size) {
                when (val student = studentsQueue.poll()) {
                    sandwichQueue.peek() -> {
                        sandwichQueue.poll()

                        failCount = 0
                    }

                    else -> {
                        studentsQueue.add(student)
                        failCount++
                    }
                }
            }

            return studentsQueue.size
        }
    }

    measureTimeMillis {
        Solution().countStudents(
            intArrayOf(1, 1, 0, 0), intArrayOf(0, 1, 0, 1)
        ).also { println("${it} should be ${it}") }
    }.also { println("Time cost: ${it}ms") }
}
