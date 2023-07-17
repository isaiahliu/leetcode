package p13xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxEvents(events: Array<IntArray>): Int {
            events.sortWith(compareBy<IntArray> { it[0] }.thenBy { it[1] })

            val awaiting = PriorityQueue<Int>()

            var eventIndex = 0
            var time = -1
            var result = 0

            while (awaiting.isNotEmpty() || eventIndex < events.size) {
                while (eventIndex < events.size && events[eventIndex][0] == time) {
                    awaiting.offer(events[eventIndex++][1])
                }

                while (awaiting.isNotEmpty() && awaiting.peek() < time) {
                    awaiting.poll()
                }

                if (awaiting.isEmpty()) {
                    events.getOrNull(eventIndex)?.also {
                        time = it[0]
                    }
                } else {
                    result++
                    time++
                    awaiting.poll()
                }
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().maxEvents(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

