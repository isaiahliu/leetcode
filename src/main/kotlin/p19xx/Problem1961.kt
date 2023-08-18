package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPrefixString(s: String, words: Array<String>): Boolean {
            var index = 0

            words.forEach {
                it.forEach {
                    if (s.getOrNull(index++) != it) {
                        return false
                    }
                }

                if (index == s.length) {
                    return true
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().isPrefixString(
            "", arrayOf()
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}