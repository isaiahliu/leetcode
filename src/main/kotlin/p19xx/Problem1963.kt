package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSwaps(s: String): Int {
            var depth = 0
            var result = 0
            var remainLeft = s.length / 2
            s.forEach {
                when {
                    it == ']' -> {
                        if (depth > 0) {
                            depth--
                        } else {
                            depth++
                            remainLeft--
                            result++
                        }
                    }

                    remainLeft > 0 -> {
                        remainLeft--
                        depth++
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minSwaps(
            "][]["
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}