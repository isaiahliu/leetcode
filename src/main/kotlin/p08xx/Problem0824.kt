package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun toGoatLatin(sentence: String): String {
            val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
            return sentence.split(" ").mapIndexed { index, s ->
                val str = StringBuilder()

                if (s[0] in vowels) {
                    str.append(s)
                } else {
                    str.append(s.drop(1)).append(s[0])
                }

                str.append("ma")

                str.append("a".repeat(index + 1))

                str.toString()
            }.joinToString(" ")
        }
    }

    measureTimeMillis {
        Solution().toGoatLatin(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}