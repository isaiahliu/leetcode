package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun letterCasePermutation(s: String): List<String> {
            if (s.isEmpty()) {
                return listOf("")
            }

            val p = hashSetOf(s[0])
            when (s[0]) {
                in 'A'..'Z' -> {
                    p.add('a' + (s[0] - 'A'))
                }

                in 'a'..'z' -> {
                    p.add('A' + (s[0] - 'a'))
                }
            }

            return letterCasePermutation(s.drop(1)).map { r -> p.map { l -> l + r } }.flatten()
        }
    }

    measureTimeMillis {
        Solution().letterCasePermutation(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}