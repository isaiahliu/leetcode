package p13xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPossible(target: IntArray): Boolean {
            if (target.size == 1) {
                return target[0] == 1
            }

            val queue = PriorityQueue<Long>(compareByDescending { it })

            var sum = 0L
            target.forEach {
                sum += it
                queue.add(it.toLong())
            }

            while (queue.peek() > 1) {
                val max = queue.poll()
                sum -= max

                if (sum == 1L) {
                    return true
                }

                val secondMax = queue.peek()

                (max - ((max - secondMax) / sum).coerceAtLeast(1) * sum).takeIf { it > 0 }?.also {
                    sum += it
                    queue.add(it)
                } ?: return false
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isPossible(
            intArrayOf(2, 1000000000)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

