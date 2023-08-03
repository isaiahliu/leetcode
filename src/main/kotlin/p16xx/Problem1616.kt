package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkPalindromeFormation(a: String, b: String): Boolean {
            fun check(left: String, right: String, useRightDefault: Boolean): Boolean {
                var leftIndex = 0
                var rightIndex = right.lastIndex

                var leftStr = left
                var rightStr = right
                while (leftIndex < rightIndex) {
                    if (leftStr[leftIndex] != rightStr[rightIndex]) {
                        if (useRightDefault) {
                            leftStr = rightStr
                        } else {
                            rightStr = leftStr
                        }
                    }

                    if (leftStr[leftIndex] != rightStr[rightIndex]) {
                        return false
                    }

                    leftIndex++
                    rightIndex--
                }

                return true
            }

            return check(a, b, true) || check(a, b, false) || check(b, a, true) || check(b, a, false)
        }
    }

    measureTimeMillis {
        Solution().checkPalindromeFormation(
            "pvhmupgqeltozftlmfjjde",
            "yjgpzbezspnnpszebzmhvp"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}