package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canBeEqual(target: IntArray, arr: IntArray): Boolean {
            target.sort()
            arr.sort()

            return target.indices.all { target[it] == arr[it] }
        }
    }

    measureTimeMillis {
        Solution().canBeEqual(
            intArrayOf(-1, -1), intArrayOf(1, 1)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

