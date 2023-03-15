package p04xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findRadius(houses: IntArray, heaters: IntArray): Int {
            val heaterMap = TreeSet(heaters.toSet())

            var result = 0

            houses.forEach { house ->
                if (house !in heaters) {
                    var nearBy = Int.MAX_VALUE
                    heaterMap.higher(house)?.also {
                        nearBy = it - house
                    }

                    heaterMap.lower(house)?.also {
                        nearBy = nearBy.coerceAtMost(house - it)
                    }

                    result = result.coerceAtLeast(nearBy)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findRadius(
            intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}