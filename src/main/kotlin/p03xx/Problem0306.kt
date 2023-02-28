package p03xx

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isAdditiveNumber(num: String): Boolean {
            fun check(previousNum2: BigInteger, previousNum1: BigInteger, startIndex: Int): Boolean {
                if (startIndex == num.length) {
                    return true
                }

                val sum = previousNum1 + previousNum2

                if (num.startsWith(sum.toString(), startIndex)) {
                    return check(previousNum1, sum, startIndex + sum.toString().length)
                }
                return false
            }

            for (i in 1 until num.length) {
                val num2 = num.substring(0, i).takeIf { it.length == 1 || it[0] != '0' }?.toBigInteger() ?: continue
                for (j in i + 1 until num.length) {
                    val num1 = num.substring(i, j).takeIf { it.length == 1 || it[0] != '0' }?.toBigInteger() ?: continue

                    if (check(num2, num1, j)) {
                        return true
                    }
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().isAdditiveNumber("1023").also { println(it) }
    }
}

