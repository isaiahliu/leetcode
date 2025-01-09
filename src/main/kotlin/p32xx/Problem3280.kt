package p32xx

import util.expect

fun main() {
    class Solution {
        fun convertDateToBinary(date: String): String {
            return date.split('-').joinToString("-") {
                it.toInt().toString(2)
            }
        }
    }

    expect {
        Solution().convertDateToBinary(
            ""
        )
    }
}
