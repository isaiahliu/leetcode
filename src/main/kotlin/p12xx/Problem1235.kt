package p12xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
            val total = TreeMap<Int, Int>()

            startTime.indices.sortedBy { endTime[it] }.forEach { jobId ->
                val start = startTime[jobId]
                val end = endTime[jobId]
                val p = profit[jobId]

                val existingMax = total.lastEntry()?.value ?: 0

                val currentMax = p + (total.lowerEntry(start + 1)?.value ?: 0)

                if (currentMax > existingMax) {
                    total[end] = currentMax
                }
            }

            return total.lastEntry()?.value ?: 0
        }
    }

    expect {
        Solution().jobScheduling(
            intArrayOf(), intArrayOf(), intArrayOf()
        )
    }
}

