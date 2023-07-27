package p14xx

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun simplifiedFractions(n: Int): List<String> {
            fun Int.fractions(): List<String> {
                val result = arrayListOf<String>()

                val bi = this.toBigInteger()
                for (i in 1 until this) {
                    if (i.toBigInteger().gcd(bi) <= BigInteger.ONE) {
                        result.add("${i}/${this}")
                    }
                }

                return result
            }

            val result = arrayListOf<String>()

            (2..n).forEach {
                result.addAll(it.fractions())
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().simplifiedFractions(
            5
        ).also { println("${it} should be 153") }

    }.also { println("Time cost: ${it}ms") }
}

