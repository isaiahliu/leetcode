package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxCompatibilitySum(students: Array<IntArray>, mentors: Array<IntArray>): Int {
            val answerSize = students[0].size
            val maxSum = students.size * answerSize
            val studentAns = students.map { it.fold(0) { a, b -> a * 2 + b } }
            val mentorAns = mentors.map { it.fold(0) { a, b -> a * 2 + b } }

            var min = Int.MAX_VALUE
            fun dfs(route: Set<Int>, sum: Int) {
                if (sum >= min) {
                    return
                }

                if (route.size == studentAns.size) {
                    min = sum
                    return
                }

                studentAns.indices.forEach {
                    if (it !in route) {
                        val value = Integer.bitCount(studentAns[it] xor mentorAns[route.size])

                        dfs(route + it, sum + value)
                    }
                }
            }

            dfs(emptySet(), 0)

            return maxSum - min
        }
    }

    measureTimeMillis {
        Solution().maxCompatibilitySum(
            arrayOf(intArrayOf(1, 0, 1)), arrayOf(intArrayOf(1, 0, 1))
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}