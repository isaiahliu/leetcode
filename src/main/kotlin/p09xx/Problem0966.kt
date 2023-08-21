package p09xx

import util.expect

fun main() {
    class Solution {
        fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
            val words = hashMapOf<Int, MutableList<String>>()
            val visited = hashSetOf<String>()
            wordlist.forEach {
                if (visited.add(it)) {
                    words.computeIfAbsent(it.length) { arrayListOf() }.add(it)
                }
            }
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
                for (word in words[it.length].orEmpty()) {
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

    expect {
        Solution().spellchecker(
            arrayOf("KiTe", "kite", "hare", "Hare"), arrayOf("keti")
        ).toList()
    }
}
