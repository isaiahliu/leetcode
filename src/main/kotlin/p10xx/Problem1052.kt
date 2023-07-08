package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSatisfied(customers: IntArray, grumpy: IntArray, minutes: Int): Int {
            var left = 0
            var right = 1

            var fixed = 0
            var max = 0
            var current = 0

            if (grumpy[0] == 1) {
                current = customers[0]
                max = current
            } else {
                fixed = customers[0]
            }

            while (right < customers.size) {
                val count = customers[right]
                if (grumpy[right] == 0) {
                    fixed += count
                } else {
                    current += count
                    while (right - left + 1 > minutes) {
                        if (grumpy[left] == 1) {
                            current -= customers[left]
                        }

                        left++
                    }

                    max = max.coerceAtLeast(current)
                }
                right++
            }

            return fixed + max
        }
    }

    measureTimeMillis {
        Solution().maxSatisfied(
            intArrayOf(1, 0, 1, 2, 1, 1, 7, 5), intArrayOf(0, 1, 0, 1, 0, 1, 0, 1), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}