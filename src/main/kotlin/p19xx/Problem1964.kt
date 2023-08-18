package p19xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestObstacleCourseAtEachPosition(obstacles: IntArray): IntArray {
            val map = TreeMap<Int, Int>()
            map[0] = 0

            return obstacles.map {
                val result = map.floorEntry(it).value + 1

                while (true) {
                    map.ceilingEntry(it)?.takeIf { it.value <= result }?.also {
                        map.remove(it.key)
                    } ?: break
                }

                map[it] = result

                result
            }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().longestObstacleCourseAtEachPosition(
            intArrayOf()
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}