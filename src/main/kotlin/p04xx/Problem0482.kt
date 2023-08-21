package p04xx

import util.expect

fun main() {
    class Solution {
        fun licenseKeyFormatting(s: String, k: Int): String {
            val str = StringBuilder()

            for (i in s.lastIndex downTo 0) {
                val c = s[i]
                if (c == '-') {
                    continue
                }

                if (str.length % (k + 1) == k) {
                    str.append("-")
                }

                str.append(c.uppercaseChar())
            }

            return str.toString().reversed()
        }
    }

    expect {
        Solution().licenseKeyFormatting(
            "2-5g-3-J", 2
        )
    }
}