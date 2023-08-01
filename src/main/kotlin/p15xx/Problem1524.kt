package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numOfSubarrays(arr: IntArray): Int {
            val m = 1000000007
            var oddCount = 0
            var evenCount = 0

            var result = 0L

            arr.forEach {
                if (it % 2 == 0) {
                    evenCount++
                } else {
                    val t = oddCount
                    oddCount = evenCount + 1
                    evenCount = t
                }

                result += oddCount

                result %= m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().numOfSubarrays(
            intArrayOf()
        ).also { println(it) }
    }
}

