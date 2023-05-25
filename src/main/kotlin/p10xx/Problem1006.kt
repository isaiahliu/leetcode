package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun clumsy(n: Int): Int {
            var result = 0

            var num = n

            var op = 1
            var temp = 0
            while (num > 0) {
                when ((n - num) % 4) {
                    0 -> {
                        temp = num
                    }

                    1 -> {
                        temp *= num
                    }

                    2 -> {
                        temp /= num

                        result += op * temp
                        temp = 0
                        op = -1
                    }

                    3 -> {
                        result += num
                    }
                }

                num--
            }

            result += temp * op

            return result
        }
    }

    measureTimeMillis {
        Solution().clumsy(
            10
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
