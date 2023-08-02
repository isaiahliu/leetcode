package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numWays(s: String): Int {
            val oneCount = s.count { it == '1' }
            val m = 1000000007
            return when {
                oneCount % 3 != 0 -> {
                    0
                }

                oneCount == 0 -> {
                    ((1L * (s.length - 1) * (s.length - 2) / 2) % m).toInt()
                }

                else -> {
                    var left = 0
                    var right = 0

                    var count = 0

                    loop@ for (char in s) {
                        if (char == '1') {
                            count++
                        }

                        when (count) {
                            oneCount / 3 -> {
                                left++
                            }

                            oneCount / 3 * 2 -> {
                                right++
                            }

                            oneCount / 3 * 2 + 1 -> {
                                break@loop
                            }
                        }
                    }

                    ((1L * left * right) % m).toInt()
                }
            }
        }
    }

    measureTimeMillis {
        Solution().numWays(
            "10101"
        ).also { println(it) }
    }
}

