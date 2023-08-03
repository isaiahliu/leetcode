package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumOddLengthSubarrays(arr: IntArray): Int {
            for (i in 1 until arr.size) {
                arr[i] += arr[i - 1]
            }

            var result = 0
            for (i in arr.indices) {
                for (j in i until arr.size step 2) {
                    result += arr[j] - (arr.getOrNull(i - 1) ?: 0)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().sumOddLengthSubarrays(
            intArrayOf()
        ).also { println("${it} should be ${it}") }
    }
}

