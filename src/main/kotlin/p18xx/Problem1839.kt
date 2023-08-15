package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestBeautifulSubstring(word: String): Int {
            val index = IntArray(26)
            index['a' - 'a'] = 0
            index['e' - 'a'] = 1
            index['i' - 'a'] = 2
            index['o' - 'a'] = 3
            index['u' - 'a'] = 4

            var result = 0
            var pre = word[0]
            var count = 0
            word.forEach {
                if (count == 0) {
                    if (it == 'a') {
                        count = 1
                    }
                } else {
                    if (index[it - 'a'] - index[pre - 'a'] in 0..1) {
                        count++
                    } else if (it == 'a') {
                        count = 1
                    } else {
                        count = 0
                    }
                }

                if (it == 'u') {
                    result = result.coerceAtLeast(count)
                }
                pre = it
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestBeautifulSubstring(
            "aeeeiiiioooauuuaeiou"
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
