package p25xx

import util.expect

fun main() {
    class Solution {
        fun vowelStrings(words: Array<String>, queries: Array<IntArray>): IntArray {
            val vowels = setOf('a', 'e', 'i', 'o', 'u')

            var sum = 0
            val sums = IntArray(words.size) {
                words[it].takeIf { it[0] in vowels && it[it.lastIndex] in vowels }?.also {
                    sum++
                }

                sum
            }

            return queries.map { (from, to) ->
                sums[to] - (sums.getOrNull(from - 1) ?: 0)
            }.toIntArray()
        }
    }

    expect {
        Solution().vowelStrings(
            arrayOf(), arrayOf()
        )
    }
}
