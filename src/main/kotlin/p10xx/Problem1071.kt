package p10xx

import util.expect

fun main() {
    class Solution {
        fun gcdOfStrings(str1: String, str2: String): String {
            val gcd = str1.length.toBigInteger().gcd(str2.length.toBigInteger()).toInt()

            var l = gcd
            while (l > 0) {
                if (gcd % l == 0) {
                    val sub = str1.take(l)

                    if (sub.repeat(str1.length / l) == str1 && sub.repeat(str2.length / l) == str2) {
                        return sub
                    }
                }

                l--
            }

            return ""
        }
    }

    expect {
        Solution().gcdOfStrings(
            "parker", "morris"
        )
    }
}