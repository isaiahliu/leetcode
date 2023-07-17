package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun breakPalindrome(palindrome: String): String {
            if (palindrome.length == 1) {
                return ""
            }

            val chars = palindrome.toCharArray()

            for (index in 0 until chars.size / 2) {
                if (chars[index] > 'a') {
                    chars[index] = 'a'
                    return String(chars)
                }
            }

            chars[chars.lastIndex]++

            return String(chars)
        }
    }

    measureTimeMillis {
        Solution().breakPalindrome(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

