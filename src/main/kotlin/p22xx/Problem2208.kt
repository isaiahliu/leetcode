package p22xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun halveArray(nums: IntArray): Int {
            val queue = PriorityQueue<Double>(compareByDescending { it })

            var total = 0.0

            nums.forEach {
                total += it
                queue.add(it.toDouble())
            }

            var result = 0

            var reduced = 0.0

            while (reduced * 2 < total) {
                (queue.poll() / 2).also {
                    reduced += it
                    queue.add(it)
                    result++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().halveArray(
            intArrayOf(3, 7, 5, 6),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

