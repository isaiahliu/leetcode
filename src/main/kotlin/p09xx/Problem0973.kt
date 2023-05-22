package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
            val queue = PriorityQueue<IntArray>(compareByDescending { (x, y) -> x * x + y * y })

            points.forEach {
                queue.add(it)

                if (queue.size > k) {
                    queue.poll()
                }
            }

            return queue.toTypedArray()
        }
    }

    measureTimeMillis {
        Solution().kClosest(
            arrayOf(), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
