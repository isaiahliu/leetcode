package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxFreq(s: String, maxLetters: Int, minSize: Int, maxSize: Int): Int {
            val map = hashMapOf<String, Int>()
            var result = 0
            for (i in 0 until s.length - minSize + 1) {
                val sub = s.substring(i until i + minSize)

                if (sub.toCharArray().distinct().size <= maxLetters) {
                    ((map[sub] ?: 0) + 1).also {
                        map[sub] = it
                        result = result.coerceAtLeast(it)
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxFreq(
            "", 1, 1, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
