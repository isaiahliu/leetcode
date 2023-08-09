package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumTeachings(n: Int, languages: Array<IntArray>, friendships: Array<IntArray>): Int {
            val users = friendships.map {
                if (languages[it[0] - 1].intersect(languages[it[1] - 1].toSet()).isNotEmpty()) {
                    emptySet()
                } else {
                    it.toSet()
                }
            }.flatten().toSet()

            var result = Int.MAX_VALUE
            for (teachLanguage in 1..n) {
                result = result.coerceAtMost(users.count { teachLanguage !in languages[it - 1] })
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minimumTeachings(
            11, arrayOf(
                intArrayOf(3, 11, 5, 10, 1, 4, 9, 7, 2, 8, 6),
                intArrayOf(5, 10, 6, 4, 8, 7),
                intArrayOf(6, 11, 7, 9),
                intArrayOf(11, 10, 4),
                intArrayOf(6, 2, 8, 4, 3),
                intArrayOf(9, 2, 8, 4, 6, 1, 5, 7, 3, 10),
                intArrayOf(7, 5, 11, 1, 3, 4),
                intArrayOf(3, 4, 11, 10, 6, 2, 1, 7, 5, 8, 9),
                intArrayOf(8, 6, 10, 2, 3, 1, 11, 5),
                intArrayOf(5, 11, 6, 4, 2)
            ), arrayOf(
                intArrayOf(7, 9),
                intArrayOf(3, 7),
                intArrayOf(3, 4),
                intArrayOf(2, 9),
                intArrayOf(1, 8),
                intArrayOf(5, 9),
                intArrayOf(8, 9),
                intArrayOf(6, 9),
                intArrayOf(3, 5),
                intArrayOf(4, 5),
                intArrayOf(4, 9),
                intArrayOf(3, 6),
                intArrayOf(1, 7),
                intArrayOf(1, 3),
                intArrayOf(2, 8),
                intArrayOf(2, 6),
                intArrayOf(5, 7),
                intArrayOf(4, 6),
                intArrayOf(5, 8),
                intArrayOf(5, 6),
                intArrayOf(2, 7),
                intArrayOf(4, 8),
                intArrayOf(3, 8),
                intArrayOf(6, 8),
                intArrayOf(2, 5),
                intArrayOf(1, 4),
                intArrayOf(1, 9),
                intArrayOf(1, 6),
                intArrayOf(6, 7)
            )
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
