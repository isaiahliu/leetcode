package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findSpecialInteger(arr: IntArray): Int {
            var pre = arr[0]
            var count = 0
            arr.forEach {
                if (pre == it) {
                    count++

                    if (count * 4 > arr.size) {
                        return it
                    }
                } else {
                    pre = it
                    count = 1
                }
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().findSpecialInteger(
            intArrayOf(0, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
