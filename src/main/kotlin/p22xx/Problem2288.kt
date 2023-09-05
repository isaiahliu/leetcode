package p22xx

import util.expect
import java.math.RoundingMode

fun main() {
    class Solution {
        fun discountPrices(sentence: String, discount: Int): String {
            val regex = "^\\$(\\d+)$".toRegex()
            return sentence.split(" ").joinToString(" ") {
                regex.matchEntire(it)?.groupValues?.getOrNull(1)?.toBigDecimalOrNull()?.let {
                    "$${(it * (100 - discount).toBigDecimal()).divide(100.toBigDecimal(), 2, RoundingMode.HALF_UP)}"
                } ?: it
            }
        }
    }

    expect {
        Solution().discountPrices(
            "a $1 $5433 b", 1
        )
    }
}