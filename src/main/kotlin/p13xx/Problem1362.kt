package p13xx

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun closestDivisors(num: Int): IntArray {
            for (left in sqrt((num + 2).toDouble()).toInt() downTo 1) {
                if ((num + 1) % left == 0) {
                    return intArrayOf(left, (num + 1) / left)
                }

                if ((num + 2) % left == 0) {
                    return intArrayOf(left, (num + 2) / left)
                }
            }

            return intArrayOf()
        }
    }

    measureTimeMillis {
        Solution().closestDivisors(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

