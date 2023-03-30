package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
            var zeroCount = 1

            var t = n
            flowerbed.forEach {
                when (it) {
                    0 -> zeroCount++
                    else -> zeroCount = 0
                }

                if (zeroCount == 3) {
                    t--
                    zeroCount -= 2
                }

                if (t == 0) {
                    return true
                }
            }

            if (zeroCount >= 2) {
                t--
            }

            return t == 0
        }
    }

    measureTimeMillis {
        Solution().canPlaceFlowers(
            intArrayOf(0, 0, 0), 2
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}