package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDeletions(s: String): Int {
            val set = hashSetOf<Int>()
            var result = 0
            s.groupingBy { it }.eachCount().values.filter { it > 0 }.sorted().forEach {
                var count = it
                while (count > 0 && !set.add(count)) {
                    count--
                    result++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minDeletions(
            "bbcebab"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}