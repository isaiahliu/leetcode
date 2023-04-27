package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSubarraySumCircular(nums: IntArray): Int {
            var sum = 0
            val sums = IntArray(nums.size * 2 + 1) {
                if (it == 0) {
                    0
                } else {
                    sum += nums[(it - 1) % nums.size]
                    sum
                }
            }

            var max = Int.MIN_VALUE

            val list = LinkedList<Int>()
            list.add(0)
            (1 until sums.size).forEach { idx ->
                if (idx - list.peek() > nums.size) {
                    list.poll()
                }

                max = max.coerceAtLeast(sums[idx] - sums[list.peek()])

                while (list.isNotEmpty() && sums[list.peekLast()] >= sums[idx]) {
                    list.pollLast()
                }

                list.add(idx)
            }

            return max
        }
    }

    measureTimeMillis {
        Solution().maxSubarraySumCircular(
            intArrayOf(-5, 3, 5)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}