package p09xx

import util.input
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
            val dic = IntArray(26)

            words2.forEach {
                val counts = IntArray(26)

                it.forEach {
                    counts[it - 'a']++
                }

                counts.forEachIndexed { index, i ->
                    dic[index] = dic[index].coerceAtLeast(i)
                }
            }

            val charCount = dic.count { it > 0 }

            fun String.match(): Boolean {
                val counts = IntArray(26)
                var matchCount = 0

                forEach {
                    counts[it - 'a']++
                    if (counts[it - 'a'] == dic[it - 'a']) {
                        matchCount++

                        if (matchCount == charCount) {
                            return true
                        }
                    }
                }

                return false
            }

            return words1.filter { it.match() }
        }
    }

    measureTimeMillis {
        Solution().wordSubsets(
            input.first().split(",").toTypedArray(),
            input.last().split(",").toTypedArray(),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}