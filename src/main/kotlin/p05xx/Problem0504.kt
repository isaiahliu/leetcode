package p05xx

import util.expect

fun main() {
    class Solution {
        fun convertToBase7(num: Int): String {
            return num.toString(7)
        }
    }

    expect {
        Solution().convertToBase7(
            100
        ).toList()
    }
}