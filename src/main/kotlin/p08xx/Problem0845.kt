package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestMountain(arr: IntArray): Int {
            var result = 0
            var incSize = 0
            var decSize = 0
            var lastNum = arr[0]

            arr.forEach {
                when {
                    it > lastNum -> {
                        if (decSize > 0) {
                            decSize = 0
                            incSize = 0
                        }
                        incSize++
                    }

                    it < lastNum -> {
                        decSize++

                        if (incSize > 0) {
                            result = result.coerceAtLeast(incSize + decSize + 1)
                        }
                    }

                    else -> {
                        incSize = 0
                        decSize = 0
                    }
                }

                lastNum = it
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestMountain(
            intArrayOf(2, 1, 4, 7, 3, 2, 5)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}