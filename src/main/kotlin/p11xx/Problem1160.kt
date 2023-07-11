package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countCharacters(words: Array<String>, chars: String): Int {
            val dic = chars.toCharArray().also { it.sort() }

            fun String.match(): Boolean {
                var dicIndex = 0
                for (c in this.toCharArray().also { it.sort() }) {
                    while ((dic.getOrNull(dicIndex++) ?: return false) != c) {
                    }
                }

                return true
            }

            var result = 0
            words.forEach {
                if (it.match()) {
                    result += it.length
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countCharacters(
            arrayOf(), ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}