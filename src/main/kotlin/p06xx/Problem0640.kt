package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun solveEquation(equation: String): String {
            fun parse(str: String): Pair<Int, Int> {
                var x = 0
                var num = 0

                var t: Int? = null
                var neg = false
                var isX = false
                "$str+".forEach {
                    when (it) {
                        'x' -> {
                            isX = true
                        }

                        '+', '-' -> {
                            if (isX) {
                                if (neg) {
                                    x -= t ?: 1
                                } else {
                                    x += t ?: 1
                                }
                            } else {
                                if (neg) {
                                    num -= t ?: 0
                                } else {
                                    num += t ?: 0
                                }
                            }

                            t = null
                            isX = false
                            neg = it == '-'
                        }

                        else -> {
                            t = (t ?: 0) * 10 + (it - '0')
                        }
                    }
                }

                return x to num
            }
            val (left, right) = equation.split("=").map { parse(it) }

            val (leftX, leftNum) = left
            val (rightX, rightNum) = right

            val x = leftX - rightX
            val num = rightNum - leftNum

            return when {
                x == 0 && num == 0 -> "Infinite solutions"
                x != 0 && num % x == 0 -> "x=${num / x}"
                else -> "No solution"
            }
        }
    }

    measureTimeMillis {
        Solution().solveEquation(
            "-x=-1"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}