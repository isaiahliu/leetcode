package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canEat(candiesCount: IntArray, queries: Array<IntArray>): BooleanArray {
            var sum = 0L
            val sums = LongArray(candiesCount.size) {
                sum += candiesCount[it]
                sum
            }

            return queries.map { (type, day, cap) ->
                sums[type] > day && (day + 1) * cap.toLong() > sums.getOrElse(type - 1) { 0L }
            }.toBooleanArray()
        }
    }

    measureTimeMillis {
        Solution().canEat(
            intArrayOf(1, 1, 1, 1, 1), arrayOf(intArrayOf(2, 2, 1))
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
