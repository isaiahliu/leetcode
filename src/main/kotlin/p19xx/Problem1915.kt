package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun wonderfulSubstrings(word: String): Long {
            val map = hashMapOf(0 to 1)

            var status = 0

            var result = 0L

            word.forEach {
                status = status xor (1 shl (it - 'a'))

                map.forEach { (pre, count) ->
                    (status xor pre).takeIf {
                        it == 0 || it == Integer.highestOneBit(it)
                    }?.also {
                        result += count
                    }
                }

                map[status] = (map[status] ?: 0) + 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().wonderfulSubstrings(
            "aba"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}