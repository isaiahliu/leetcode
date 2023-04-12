package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxChunksToSorted(arr: IntArray): Int {
            val queue = LinkedList<Pair<Int, Int>>()

            arr.forEach { num ->
                var last = queue.peekLast()?.second?.coerceAtLeast(num) ?: num
                while (queue.isNotEmpty() && queue.peekLast().second > num) {
                    queue.pollLast()
                }

                queue.add(num to last)
            }

            return queue.size
        }
    }

    measureTimeMillis {
        Solution().maxChunksToSorted(
            intArrayOf(1, 0, 1, 3, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}