package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mincostTickets(days: IntArray, costs: IntArray): Int {
            val map = TreeMap<Int, Int>()
            map[0] = 0

            fun schedule(toDay: Int, totalCost: Int) {
                while (true) {
                    map.lowerEntry(toDay)?.takeIf { it.value >= totalCost }?.key?.also {
                        map.remove(it)
                    } ?: break
                }

                if (map.higherEntry(toDay - 1)?.value?.takeIf { it < totalCost } == null) {
                    map[toDay] = totalCost
                }
            }

            schedule(days[0], costs[0])
            schedule(days[0] + 6, costs[1])
            schedule(days[0] + 29, costs[2])

            for (i in 1 until days.size) {
                val lastDay = days[i - 1]

                val (d, cost) = map.higherEntry(lastDay - 1) ?: return -1

                val today = days[i]
                if (d < today) {
                    schedule(today, cost + costs[0])
                    schedule(today + 6, cost + costs[1])
                    schedule(today + 29, cost + costs[2])
                }
            }

            return map.higherEntry(days[days.lastIndex] - 1).value
        }
    }

    measureTimeMillis {
        Solution().mincostTickets(
            intArrayOf(1, 4, 6, 7, 8, 20), intArrayOf(2, 7, 15)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
