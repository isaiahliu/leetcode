package p35xx

import util.expect

fun main() {
    class Solution {
        fun maxFreqSum(s: String): Int {
            val vowels = setOf('a', 'e', 'i', 'o', 'u')
            val counts = s.groupingBy { it }.eachCount()

            return (counts.filter { it.key !in vowels }.maxOfOrNull { it.value } ?: 0) + (counts.filter { it.key in vowels }.maxOfOrNull { it.value } ?: 0)
        }
    }

    expect {
        Solution().maxFreqSum(
            ""
        )
    }
}
