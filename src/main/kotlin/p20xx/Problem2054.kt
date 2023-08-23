package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxTwoEvents(events: Array<IntArray>): Int {
            val leftMax = TreeMap<Int, Int>()

            events.sortBy { it[1] }
            var max = 0
            events.forEach { (_, end, value) ->
                if (value > max) {
                    max = value
                    leftMax[end] = max
                }
            }

            max = 0
            var result = 0

            events.sortByDescending { it[0] }
            events.forEach { (start, _, value) ->
                max = max.coerceAtLeast(value)

                result = result.coerceAtLeast(max + (leftMax.lowerEntry(start)?.value ?: 0))
            }

            return result
        }
    }

    expect {
        Solution().maxTwoEvents(
            arrayOf(
                intArrayOf(1, 3, 2), intArrayOf(4, 5, 2), intArrayOf(2, 4, 3)
            )
        )
    }
}