package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun commonChars(words: Array<String>): List<String> {
            val intersect = IntArray(26) { Int.MAX_VALUE }
            words.forEach {
                val counts = IntArray(26)

                it.forEach {
                    counts[it - 'a']++
                }

                counts.forEachIndexed { index, i ->
                    intersect[index] = intersect[index].coerceAtMost(i)
                }
            }

            val result = arrayListOf<String>()

            intersect.forEachIndexed { index, i ->
                repeat(i) {
                    result.add(('a' + index).toString())
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().commonChars(
            arrayOf()
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
