package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mincostToHireWorkers(quality: IntArray, wage: IntArray, k: Int): Double {
            if (k == 1) {
                return wage.min().toDouble()
            }

            val headQueue = PriorityQueue<Int>(compareBy { wage[it].toDouble() / quality[it] })
            quality.indices.forEach {
                headQueue.add(it)
            }

            var result = Double.MAX_VALUE
            val queueK1 = PriorityQueue<Int>(compareByDescending { it })

            var head = headQueue.poll()
            var qualitySum = 0
            while (headQueue.isNotEmpty()) {
                queueK1.add(quality[head].also { qualitySum += it })

                if (queueK1.size == k) {
                    qualitySum -= queueK1.poll()
                }

                head = headQueue.poll()

                if (queueK1.size == k - 1) {
                    val unitCost = wage[head].toDouble() / quality[head].toDouble()

                    result = result.coerceAtMost((qualitySum + quality[head]) * unitCost)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().mincostToHireWorkers(
            intArrayOf(), intArrayOf(), 1
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}