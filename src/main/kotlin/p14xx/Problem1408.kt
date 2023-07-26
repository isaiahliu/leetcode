package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun stringMatching(words: Array<String>): List<String> {
            words.sortWith(compareByDescending { it.length })

            val visited = hashSetOf<String>()

            val result = arrayListOf<String>()
            words.forEach {
                if (visited.any { p -> it in p }) {
                    result.add(it)
                }

                visited.add(it)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().stringMatching(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

