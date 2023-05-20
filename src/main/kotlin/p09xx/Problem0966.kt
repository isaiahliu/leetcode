package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
            val words = wordlist.distinct()
            val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
            val EXACT = 0
            val CASE = 1
            val VOWEL = 2
            val MISMATCH = 3

            fun String.match(query: String): Int {
                return when {
                    this == query -> EXACT
                    this.lowercase() == query.lowercase() -> CASE
                    length != query.length -> MISMATCH
                    else -> {
                        var problem = VOWEL

                        loop@ for (i in indices) {
                            when {
                                this[i].lowercaseChar() == query[i].lowercaseChar() -> {}
                                this[i] in vowels && query[i] in vowels -> {}
                                else -> {
                                    problem = MISMATCH
                                    break@loop
                                }
                            }
                        }

                        problem
                    }
                }
            }

            return queries.map {
                var problem = MISMATCH
                var match = ""
                for (word in words) {
                    val m = word.match(it)
                    if (m < problem) {
                        problem = m
                        match = word
                    }

                    if (m == EXACT) {
                        break
                    }
                }
                match
            }.toTypedArray()
        }
    }

    measureTimeMillis {
        Solution().spellchecker(
            arrayOf("KiTe", "kite", "hare", "Hare"), arrayOf("keti")
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
