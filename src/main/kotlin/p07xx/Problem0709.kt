package p07xx

import util.expect

fun main() {
    class Solution {
        fun toLowerCase(s: String): String {
            return String(s.map {
                if (it in 'A'..'Z') {
                    'a' + (it - 'A')
                } else {
                    it
                }
            }.toCharArray())
        }
    }

    expect {
        Solution().toLowerCase("here")
    }
}