package p01xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun fractionToDecimal(numerator: Int, denominator: Int): String {
            val numLong = numerator.toLong().absoluteValue
            val denLong = denominator.toLong().absoluteValue

            val neg = numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0

            var t = numLong % denLong

            var integerPart = (numLong / denLong).absoluteValue.toString()

            if (t == 0L) {
                return if (integerPart == "0") {
                    integerPart
                } else {
                    (if (neg) "-" else "") + integerPart
                }
            }

            integerPart = (if (neg) "-" else "") + integerPart

            val decimalPart = StringBuilder()

            val cache = hashMapOf<Long, Int>()
            var pos = 0
            while (t != 0L && t !in cache) {
                cache[t] = pos++

                t *= 10
                decimalPart.append((t / denLong).absoluteValue)

                t %= denLong
            }

            return if (t > 0) {
                val p = cache[t] ?: 0
                "${integerPart}.${decimalPart.substring(0, p)}(${decimalPart.substring(p)})"
            } else {
                "${integerPart}.$decimalPart"
            }
        }
    }

    expect {
        Solution().fractionToDecimal(
            Int.MIN_VALUE, -1
        )
    }
}

