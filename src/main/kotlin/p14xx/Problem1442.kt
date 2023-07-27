package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countTriplets(arr: IntArray): Int {
            val counts = Array(arr.size) { hashMapOf<Int, Int>() }
            counts[0][arr[0]] = 1

            var result = 0
            for (i in 1 until arr.size) {
                var current = 0

                for (offset in 0 until i) {
                    current = current xor arr[i - offset]

                    counts[i - offset - 1][current]?.also {
                        result += it
                    }
                }

                counts[i][arr[i]] = 1
                counts[i - 1].forEach { (t, c) ->
                    counts[i][t xor arr[i]] = (counts[i][t xor arr[i]] ?: 0) + c
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countTriplets(
            intArrayOf(2, 3, 1, 6, 7)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

