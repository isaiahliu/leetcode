package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minInterval(intervals: Array<IntArray>, queries: IntArray): IntArray {
            var intervalIndex = 0
            intervals.sortWith(compareBy<IntArray> { it[0] }.thenBy { it[1] })

            val sorted = queries.indices.sortedBy { queries[it] }

            val queue = PriorityQueue<IntArray>(compareBy { it[1] - it[0] })

            val result = IntArray(queries.size)
            sorted.forEach { index ->
                val q = queries[index]

                while (intervalIndex < intervals.size && intervals[intervalIndex][0] <= q) {
                    queue.add(intervals[intervalIndex++])
                }

                while (queue.isNotEmpty() && queue.peek()[1] < q) {
                    queue.poll()
                }

                result[index] = queue.peek()?.let { it[1] - it[0] + 1 } ?: -1
            }

            return result
        }
    }

    expect {
        Solution().minInterval(
            arrayOf(
                intArrayOf(1, 4),
                intArrayOf(2, 4),
                intArrayOf(3, 6),
                intArrayOf(4, 4),
            ),
            intArrayOf(2, 3, 4, 5)
        ).toList()
    }
}

