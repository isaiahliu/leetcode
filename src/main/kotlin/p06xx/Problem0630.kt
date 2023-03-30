package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun scheduleCourse(courses: Array<IntArray>): Int {
            courses.sortWith(compareBy<IntArray> { it[1] }.thenBy { it[0] })

            val dp = IntArray(courses.size + 1)
            var count = 0

            courses.forEach { (cost, deadline) ->
                if (dp[count] + cost <= deadline) {
                    count++
                    dp[count] = dp[count - 1] + cost
                }

                for (i in count downTo 1) {
                    val t = dp[i - 1] + cost

                    if (t <= deadline) {
                        dp[i] = dp[i].coerceAtMost(t)
                    }
                }
            }

            return count
        }
    }

    measureTimeMillis {
        Solution().scheduleCourse(
            arrayOf(
                intArrayOf(9, 10),
                intArrayOf(3, 12),
                intArrayOf(7, 17),
                intArrayOf(4, 18),
                intArrayOf(10, 19),
                intArrayOf(5, 20),
                intArrayOf(10, 20),
            )
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}