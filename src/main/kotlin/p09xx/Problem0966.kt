package p09xx

import util.expect

fun main() {
    class Solution {
        fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
            val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

            fun String.toWildCardVowel(): String {
                return buildString {
                    this@toWildCardVowel.forEach {
                        if (it in vowels) {
                            append('*')
                        } else {
                            append(it.lowercaseChar())
                        }
                    }
                }
            }

            val stricts = hashSetOf<String>()
            val ignoreCases = hashMapOf<String, Int>()
            val ignoreVowels = hashMapOf<String, Int>()
            wordlist.forEachIndexed { index, str ->
                stricts += str
                ignoreCases.putIfAbsent(str.lowercase(), index)

                ignoreVowels.putIfAbsent(str.toWildCardVowel(), index)
            }

            return Array(queries.size) {
                val query = queries[it]

                if (query in stricts) {
                    return@Array query
                }

                ignoreCases[query.lowercase()]?.also {
                    return@Array wordlist[it]
                }

                ignoreVowels[query.toWildCardVowel()]?.also {
                    return@Array wordlist[it]
                }

                return@Array ""
            }
        }
    }

    expect {
        Solution().spellchecker(
            arrayOf("iiy", "aay"), arrayOf("uay")
        ).toList()
    }
}
