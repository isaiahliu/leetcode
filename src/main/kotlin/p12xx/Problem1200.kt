package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumAbsDifference(arr: IntArray): List<List<Int>> {
            arr.sort()

            var min = Int.MAX_VALUE

            val result = arrayListOf<List<Int>>()

            for (i in 1 until arr.size) {
                val last = arr[i - 1]
                val current = arr[i]
                when {
                    current - last < min -> {
                        min = current - last
                        result.clear()
                        result.add(listOf(last, current))
                    }

                    current - last == min -> {
                        result.add(listOf(last, current))
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minimumAbsDifference(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
