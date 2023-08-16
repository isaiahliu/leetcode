package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun makeEqual(words: Array<String>): Boolean {
            val counts = IntArray(26)

            words.forEach {
                it.forEach {
                    counts[it - 'a']++
                }
            }

            return counts.all { it % words.size == 0 }
        }
    }

    measureTimeMillis {
        Solution().makeEqual(
            arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
