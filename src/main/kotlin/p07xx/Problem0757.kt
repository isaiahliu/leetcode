package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun intersectionSizeTwo(intervals: Array<IntArray>): Int {
            intervals.sortWith(compareBy<IntArray> { it[0] }.thenBy { it[1] })

            val map = LinkedList<IntArray>()

            intervals.forEach { interval ->
                map.peekLast()?.takeIf { it[0] == interval[0] && it[1] < interval[1] }?.also {
                    return@forEach
                }

                while (true) {
                    map.peekLast()?.takeIf { it[1] >= interval[1] }?.also {
                        map.pollLast()
                    } ?: break
                }

                map.add(interval)
            }
            var result = 0

            val set = hashSetOf<Int>()

            while (map.isNotEmpty()) {
                val (start, end) = map.poll()

                val coveredCount = set.count { it in start..end }.coerceAtMost(2)

                (0 until 2 - coveredCount).forEach {
                    set.add(end - it)
                    result++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().intersectionSizeTwo(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(3, 7),
                intArrayOf(8, 9),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}