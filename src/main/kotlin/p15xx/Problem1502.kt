package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canMakeArithmeticProgression(arr: IntArray): Boolean {
            arr.sort()

            val delta = arr[1] - arr[0]

            for (i in 2 until arr.size) {
                if (arr[i] - arr[i - 1] != delta) {
                    return false
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().canMakeArithmeticProgression(
            intArrayOf(-19, -12)
        ).also { println(it) }
    }
}

