package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun threeConsecutiveOdds(arr: IntArray): Boolean {
            var oddCount = 0

            arr.forEach {
                if (it and 1 > 0) {
                    oddCount++
                } else {
                    oddCount = 0
                }

                if (oddCount == 3) {
                    return true
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().threeConsecutiveOdds(
            intArrayOf()
        ).also { println(it) }
    }
}

