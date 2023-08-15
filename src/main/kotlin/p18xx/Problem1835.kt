package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getXORSum(arr1: IntArray, arr2: IntArray): Int {
            return arr1.fold(0) { a, b -> a xor b } and arr2.fold(0) { a, b -> a xor b }
        }
    }

    measureTimeMillis {
        Solution().getXORSum(
            intArrayOf(1, 2, 3), intArrayOf(6, 5)
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
