package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeOccurrences(s: String, part: String): String {
            var t = s
            while (true) {
                t.replaceFirst(part, "").takeIf {
                    it.length < t.length
                }?.also {
                    t = it
                } ?: break
            }

            return t
        }
    }

    measureTimeMillis {
        Solution().removeOccurrences(
            "", ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
