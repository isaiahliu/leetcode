package p17xx

import util.expect

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

    expect {
        Solution().countStudents(
            intArrayOf(1, 1, 0, 0), intArrayOf(0, 1, 0, 1)
        )
    }
}
