package p01xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun singleNumber(nums: IntArray): Int {
            infix fun Long.xor3(target: Long): Long {
                var result = 0L

                var left = this
                var right = target

                var base = 1
                while (left > 0 || right > 0) {
                    val d = (left % 3 + right % 3) % 3

                    result += d * base

                    base *= 3
                    left /= 3
                    right /= 3
                }

                return result
            }

            var sign = 0
            val result = nums.fold(0L) { a, b ->
                if (b < 0) {
                    sign++
                }

                a xor3 b.toLong().absoluteValue
            }

            return if (sign % 3 == 0) {
                result
            } else {
                -result
            }.toInt()
        }
    }

    measureTimeMillis {
        Solution().singleNumber(
            intArrayOf(-2, -2, 1, 1, 4, 1, 4, 4, -4, -2)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

