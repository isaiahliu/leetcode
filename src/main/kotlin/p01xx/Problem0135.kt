package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun candy(ratings: IntArray): Int {
            var current = Int.MAX_VALUE
            var size = 1

            val incArr = IntArray(ratings.size) {
                val r = ratings[it]

                if (r > current) {
                    size++
                } else {
                    size = 1
                }

                current = r

                size
            }

            current = Int.MAX_VALUE
            size = 1

            var sum = 0

            for (i in ratings.lastIndex downTo 0) {
                val r = ratings[i]

                if (r > current) {
                    size++
                } else {
                    size = 1
                }

                current = r

                sum += size.coerceAtLeast(incArr[i])
            }

            return sum
        }
    }

    measureTimeMillis {
        Solution().candy(
            intArrayOf(1, 2, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

