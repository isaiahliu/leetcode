package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun oddString(words: Array<String>): String {
            for (i in 1 until words[0].length) {
                val map = hashMapOf<Int, MutableSet<Int>>()

                words.forEachIndexed { index, s ->
                    map.computeIfAbsent(s[i] - s[i - 1]) { hashSetOf() }.add(index)
                }

                if (map.size > 1) {
                    return map.values.firstOrNull { it.size == 1 }?.firstOrNull()?.let { words[it] }.orEmpty()
                }
            }

            return ""
        }
    }

    measureTimeMillis {
        Solution().oddString(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
