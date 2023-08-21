package p05xx

import util.expect

fun main() {
    class Solution {
        fun findLongestWord(s: String, dictionary: List<String>): String {
            fun subContains(str1: String, str2: String): Boolean {
                var index1 = 0
                var index2 = 0

                while (index1 < str1.length && index2 < str2.length) {
                    if (str1[index1++] == str2[index2]) {
                        index2++
                    }
                }

                return index2 == str2.length
            }

            dictionary.filter { it.length <= s.length }.groupBy { it.length }.entries.sortedByDescending { it.key }
                .forEach { (_, strs) ->
                    strs.sorted().firstOrNull { subContains(s, it) }?.also {
                        return it
                    }
                }

            return ""
        }
    }

    expect {
        Solution().findLongestWord("", listOf())
    }
}