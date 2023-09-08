package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
            val result = IntArray(n)

            val idle = TreeSet<Int>()
            idle.addAll(result.indices)

            val ends = LongArray(n)
            val busy = PriorityQueue(compareBy<Int> { ends[it] }.thenBy { it })

            meetings.sortBy { it[0] }

            var now = 0L
            meetings.forEach { (start, end) ->
                while (busy.isNotEmpty() && ends[busy.peek()] <= start || idle.isEmpty()) {
                    now = ends[busy.poll().also {
                        idle.add(it)
                    }]
                }

                now = now.coerceAtLeast(start.toLong())

                idle.pollFirst()?.also {
                    ends[it] = now + (end - start)
                    busy.add(it)
                    result[it]++
                }
            }

            return result.indices.maxWith(compareBy<Int> { result[it] }.thenByDescending { it })
        }
    }

    expect {
        Solution().mostBooked(
            2, arrayOf(
                intArrayOf(0, 10), intArrayOf(1, 5), intArrayOf(2, 7), intArrayOf(3, 4)
            )
        )
    }
}