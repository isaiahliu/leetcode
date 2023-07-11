package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun tribonacci(n: Int): Int {
            val queue = LinkedList<Int>()
            queue.add(0)
            queue.add(1)
            queue.add(1)

            if (n < 3) {
                return queue[n]
            }

            var sum = 2

            repeat(n - 2) {
                queue.add(sum)

                sum *= 2
                sum -= queue.poll()
            }

            return queue.peekLast()
        }
    }

    measureTimeMillis {
        Solution().tribonacci(
            25
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}