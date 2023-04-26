package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numSpecialEquivGroups(words: Array<String>): Int {
            fun String.group(): String {
                val odd = String(this.filterIndexed { index, c -> index % 2 == 1 }.toCharArray().also { it.sort() })
                val even = String(this.filterIndexed { index, c -> index % 2 == 0 }.toCharArray().also { it.sort() })

                return "${odd}_${even}"
            }

            return words.map { it.group() }.toSet().size
        }
    }

    measureTimeMillis {
        Solution().numSpecialEquivGroups(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}