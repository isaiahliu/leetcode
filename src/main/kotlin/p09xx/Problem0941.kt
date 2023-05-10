package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun validMountainArray(arr: IntArray): Boolean {
            if (arr.size < 3 || arr[0] > arr[1]) {
                return false
            }

            var pre = -1
            var inc = true

            arr.forEach {
                when {
                    it == pre -> return false
                    inc && it < pre -> inc = false
                    !inc && it > pre -> return false
                }

                pre = it
            }

            return !inc
        }
    }

    measureTimeMillis {
        Solution().validMountainArray(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}