package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minGroups(intervals: Array<IntArray>): Int {
            val treeMap = TreeMap<Int, Int>()

            intervals.forEach { (from, to) ->
                treeMap[from] = (treeMap[from] ?: 0) + 1
                treeMap[to + 1] = (treeMap[to + 1] ?: 0) - 1
            }

            var result = 0

            var sum = 0
            treeMap.forEach { (_, count) ->
                sum += count
                result = result.coerceAtLeast(sum)
            }

            return result
        }
    }

    expect {
        Solution().minGroups(
            arrayOf(
                intArrayOf(5, 10), intArrayOf(6, 8), intArrayOf(1, 5), intArrayOf(2, 3), intArrayOf(1, 10)
            )
        )
    }
}