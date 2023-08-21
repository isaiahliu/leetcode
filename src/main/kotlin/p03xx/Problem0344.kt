package p03xx

import util.expect

fun main() {
    class Solution {
        fun reverseString(s: CharArray): Unit {
            val size = s.size
            for (i in 0 until size / 2) {
                val t = s[i]
                s[i] = s[size - i - 1]
                s[size - i - 1] = t
            }
        }
    }

    expect {
        Solution().reverseString(
            charArrayOf()
        )
    }
}

