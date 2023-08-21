package p09xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun mincostTickets(days: IntArray, costs: IntArray): Int {
            val map = TreeMap<Int, Int>()
            map[0] = 0

            val periods = arrayOf(0, 6, 29)

            fun schedule(today: Int, costed: Int) {
                periods.forEachIndexed { index, p ->
                    val endDay = today + p
                    val totalCost = costed + costs[index]

                    while (true) {
                        map.lowerEntry(endDay)?.takeIf { it.value >= totalCost }?.key?.also {
                            map.remove(it)
                        } ?: break
                    }

                    if (map.higherEntry(endDay - 1)?.value?.takeIf { it < totalCost } == null) {
                        map[endDay] = totalCost
                    }
                }
            }

            schedule(days[0], 0)

            for (i in 1 until days.size) {
                val today = days[i]

                map.higherEntry(days[i - 1] - 1)?.takeIf { it.key < today }?.value?.also {
                    schedule(today, it)
                }
            }

            return map.higherEntry(days[days.lastIndex] - 1).value
        }
    }

    expect {
        Solution().mincostTickets(
            intArrayOf(1, 4, 6, 7, 8, 20), intArrayOf(2, 7, 15)
        )
    }
}
