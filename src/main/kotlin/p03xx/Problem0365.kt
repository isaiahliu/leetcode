package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canMeasureWater(jug1Capacity: Int, jug2Capacity: Int, targetCapacity: Int): Boolean {
            val visited = hashSetOf<Pair<Int, Int>>()

            fun process(left: Int, right: Int): Boolean {
                if (!visited.add(left to right)) {
                    return false
                }

                if (left == targetCapacity || right == targetCapacity || left + right == targetCapacity) {
                    return true
                }

                if (left > 0 && process(0, right)) {
                    return true
                }

                if (right > 0 && process(left, 0)) {
                    return true
                }

                if (left < jug1Capacity) {
                    if (process(jug1Capacity, right)) {
                        return true
                    }

                    val t = (jug1Capacity - left).coerceAtMost(right)

                    if (process(left + t, right - t)) {
                        return true
                    }
                }

                if (right < jug2Capacity) {
                    if (process(left, jug2Capacity)) {
                        return true
                    }

                    val t = (jug2Capacity - right).coerceAtMost(left)

                    if (process(left - t, right + t)) {
                        return true
                    }
                }

                return false
            }

            return process(0, 0)
        }
    }

    measureTimeMillis {
        Solution().canMeasureWater(
            2, 6, 5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

