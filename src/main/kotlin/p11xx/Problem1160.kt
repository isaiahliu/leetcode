package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countCharacters(words: Array<String>, chars: String): Int {
            val dic = chars.groupingBy { it }.eachCount()

            var result = 0
            words.forEach {
                if (it.groupingBy { it }.eachCount().all { (c, count) -> (dic[c] ?: 0) >= count }) {
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