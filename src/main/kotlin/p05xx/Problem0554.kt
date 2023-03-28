package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun leastBricks(wall: List<List<Int>>): Int {
            val map = hashMapOf<Int, Int>()

            wall.forEach { w ->
                var space = w[0]

                w.drop(1).forEach {
                    map[space] = (map[space] ?: 0) + 1

                    space += it
                }
            }

            return wall.size - (map.values.maxOrNull() ?: 0)
        }
    }

    measureTimeMillis {
        Solution().leastBricks(
            listOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}