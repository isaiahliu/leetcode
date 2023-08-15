package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getOrder(tasks: Array<IntArray>): IntArray {
            val waitingQueue = PriorityQueue(compareBy<Int> { tasks[it][0] })
            val processQueue = PriorityQueue(compareBy<Int> { tasks[it][1] }.thenBy { it })

            tasks.indices.forEach { waitingQueue.add(it) }

            var time = 0

            val result = IntArray(tasks.size)
            var processIndex = 0
            while (processIndex < result.size) {
                if (processQueue.isEmpty()) {
                    waitingQueue.peek()?.also {
                        time = time.coerceAtLeast(tasks[it][0])
                    }
                }

                while (waitingQueue.isNotEmpty() && tasks[waitingQueue.peek()][0] <= time) {
                    processQueue.add(waitingQueue.poll())
                }

                processQueue.poll()?.also {
                    result[processIndex++] = it
                    time += tasks[it][1]
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().getOrder(
            arrayOf()
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
