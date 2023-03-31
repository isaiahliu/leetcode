package p06xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun predictPartyVictory(senate: String): String {
            val queues = Array(2) { LinkedList<Int>() }
            val voted = Array(2) { LinkedList<Int>() }
            val ranges = 0..1
            senate.forEachIndexed { index, c ->
                when (c) {
                    'R' -> queues[0].add(index)
                    'D' -> queues[1].add(index)
                }
            }

            while (queues.all { it.isNotEmpty() }) {
                while (queues.any { it.isNotEmpty() }) {
                    var nextIndex = 0

                    if (queues[0].isEmpty() || queues[1].isNotEmpty() && queues[1].peek() < queues[0].peek()) {
                        nextIndex = 1
                    }

                    voted[nextIndex].add(queues[nextIndex].pop())
                    queues[1 - nextIndex].poll() ?: voted[1 - nextIndex].poll() ?: break
                }

                ranges.forEach {
                    queues[it].addAll(voted[it])
                    voted[it].clear()
                }
            }

            return if (queues[0].isNotEmpty()) {
                "Radiant"
            } else {
                "Dire"
            }
        }
    }

    measureTimeMillis {
        Solution().predictPartyVictory(
            "RDD"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}