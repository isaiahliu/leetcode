package p22xx

import util.expect

fun main() {
    class Solution {
        fun largestGoodInteger(num: String): String {
            return "(.)\\1{2}".toRegex().findAll(num).map {
                it.value
            }.maxOrNull().orEmpty()
        }
    }

    expect {
        Solution().largestGoodInteger(
            "abbbccccca"
        )
    }
}