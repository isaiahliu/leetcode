package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canConstruct(ransomNote: String, magazine: String): Boolean {
            val counts = IntArray(26)

            magazine.forEach {
                counts[it - 'a']++
            }

            for (c in ransomNote) {
                if (counts[c - 'a']-- == 0) {
                    return false
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().canConstruct(
            "aa", "aab"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

