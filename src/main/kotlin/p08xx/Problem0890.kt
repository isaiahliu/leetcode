package p08xx

import util.expect

fun main() {
    class Solution {
        fun findAndReplacePattern(words: Array<String>, pattern: String): List<String> {
            fun String.similar(target: String): Boolean {
                val map1 = hashMapOf<Char, Char>()
                val map2 = hashMapOf<Char, Char>()
                indices.forEach {
                    val l = this[it]
                    val r = target[it]

                    if (map1[l] == null && map2[r] == null) {
                        map1[l] = r
                        map2[r] = l
                    } else if (map1[l] != r || map2[r] != l) {
                        return false
                    }

                }
                return true
            }

            return words.filter { it.similar(pattern) }
        }
    }

    expect {
        Solution().findAndReplacePattern(
            arrayOf(), ""
        )
    }
}