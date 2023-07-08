package p10xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rearrangeBarcodes(barcodes: IntArray): IntArray {
            val queue = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })

            barcodes.toList().groupingBy { it }.eachCount().forEach { (num, count) ->
                queue.add(num to count)
            }

            var last = 0
            return IntArray(barcodes.size) {
                val top = queue.poll()

                if (top.first == last) {
                    val second = queue.poll()

                    queue.add(top)
                    queue.add(second.first to second.second - 1)

                    second.first
                } else {
                    queue.add(top.first to top.second - 1)
                    top.first
                }.also {
                    last = it
                }
            }
        }
    }

    measureTimeMillis {
        Solution().rearrangeBarcodes(
            intArrayOf(1, 0, 1, 2, 1, 1, 7, 5)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}