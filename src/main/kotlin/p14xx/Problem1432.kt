package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxDiff(num: Int): Int {
            val numStr = num.toString()

            var maxIndex = -1
            var minIndex = -1
            numStr.forEachIndexed { index, c ->
                if (maxIndex < 0) {
                    if (c < '9') {
                        maxIndex = index
                    }
                }

                if (minIndex < 0) {
                    if (index == 0 && c > '1' || index > 0 && c > '0' && c != numStr[0]) {
                        minIndex = index
                    }
                }
            }

            val max = if (maxIndex >= 0) {
                String(numStr.map {
                    if (it == numStr[maxIndex]) {
                        '9'
                    } else {
                        it
                    }
                }.toCharArray()).toInt()
            } else {
                num
            }

            val min = if (minIndex >= 0) {
                String(numStr.map {
                    if (it == numStr[minIndex]) {
                        if (minIndex == 0) {
                            '1'
                        } else {
                            '0'
                        }
                    } else {
                        it
                    }
                }.toCharArray()).toInt()
            } else {
                num
            }

            return max - min
        }
    }

    measureTimeMillis {
        Solution().maxDiff(
            123456
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

