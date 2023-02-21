package p01xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun singleNumber(nums: IntArray): Int {
            infix fun Long.xor3(target: Long): Long {
                val from = this.toString(3).padStart(20, '0')
                val to = target.toString(3).padStart(20, '0')

                if (from.isEmpty() && to.isEmpty()) {
                    return 0
                }

                return String(CharArray(20) {
                    '0' + ((from[it] - '0') + (to[it] - '0')) % 3
                }).toLong(3)
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

