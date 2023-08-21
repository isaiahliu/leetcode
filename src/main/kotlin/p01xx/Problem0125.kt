package p01xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun isPalindrome(s: String): Boolean {
            val newStr = s.lowercase(Locale.getDefault()).filter { it in 'a'..'z' || it in '0'..'9' }

            return newStr == newStr.reversed()
        }
    }

    expect {
        Solution().isPalindrome("")
    }
}

