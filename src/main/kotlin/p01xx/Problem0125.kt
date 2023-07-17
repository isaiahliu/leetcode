package p01xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPalindrome(s: String): Boolean {
            val newStr = s.lowercase(Locale.getDefault()).filter { it in 'a'..'z' || it in '0'..'9' }

            return newStr == newStr.reversed()
        }
    }

    measureTimeMillis {
        Solution().isPalindrome("").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

