package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun pathInZigZagTree(label: Int): List<Int> {
            var start = 1
            var direction = true

            while (label >= start * 2) {
                start *= 2
                direction = !direction
            }

            val result = LinkedList<Int>()
            var num = label

            while (start > 0) {
                result.push(num)

                num -= start

                if (!direction) {
                    num = start - num - 1
                }

                direction = !direction
                start /= 2
                num /= 2

                if (!direction) {
                    num = start - num - 1
                }
                num += start
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().pathInZigZagTree(
            14
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}