package p10xx

import util.expect

fun main() {
    class Solution {
        fun queryString(s: String, n: Int): Boolean {
            if (n == 0) {
                return true
            }

            val b = n.toString(2)

            if (b !in s) {
                return false
            }

            return queryString(s, n - 1)
        }
    }

    expect {
        Solution().queryString(
            "100", 100
        )
    }
}
