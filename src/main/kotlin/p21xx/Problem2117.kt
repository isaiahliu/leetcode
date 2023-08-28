package p21xx

import util.expect
import java.math.BigInteger

fun main() {
    class Solution {
        fun abbreviateProduct(left: Int, right: Int): String {
            var result = BigInteger.ONE
            for (num in left..right) {
                result *= num.toBigInteger()
            }

            val s = result.toString()
            val str = s.trimEnd('0')

            return when {
                str.length > 10 -> "${str.take(5)}...${str.takeLast(5)}"
                else -> str
            } + "e${s.length - str.length}"
        }
    }

    expect {
        Solution().abbreviateProduct(
            5, 6
        )
    }
}