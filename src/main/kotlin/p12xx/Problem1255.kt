package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxScoreWords(words: Array<String>, letters: CharArray, score: IntArray): Int {
            val letterCounts = IntArray(26)

            letters.forEach {
                letterCounts[it - 'a']++
            }

            fun dfs(total: String, index: Int): Int {
                val word = words.getOrNull(index) ?: return 0

                var result = dfs(total, index + 1)

                val count = IntArray(26)
                total.forEach {
                    count[it - 'a']++
                }

                var s = 0
                word.forEach {
                    count[it - 'a']++
                    s += score[it - 'a']

                    if (count[it - 'a'] > letterCounts[it - 'a']) {
                        return result
                    }
                }

                result = result.coerceAtLeast(s + dfs(total + word, index + 1))

                return result
            }

            return dfs("", 0)
        }
    }

    measureTimeMillis {
        Solution().maxScoreWords(
            arrayOf(), charArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
