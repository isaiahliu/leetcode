package pinter05

import util.expect

fun main() {
    class Solution {
        fun printBin(num: Double): String {
            val result = StringBuilder()

            var t = num * 2
            while (t > 0.0 && result.length < 31) {
                if (t >= 1) {
                    result.append(1)
                    t -= 1
                } else {
                    result.append(0)
                }

                t *= 2
            }

            return when {
                t > 0 -> {
                    "ERROR"
                }

                result.isEmpty() -> {
                    "0"
                }

                else -> {
                    "0.${result}"
                }
            }
        }
    }

    expect {
        Solution().printBin(0.625).toList()
    }
}

