package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkPowersOfThree(n: Int): Boolean {
            var t = n
            while (t > 0) {
                when (t % 3) {
                    0 -> {
                        t /= 3
                    }

                    1 -> {
                        t = (t - 1) / 3
                    }

                    else -> {
                        return false
                    }
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().checkPowersOfThree(
            91
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
