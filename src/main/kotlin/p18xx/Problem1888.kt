package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minFlips(s: String): Int {
            val left = Array(s.length) {
                intArrayOf(0, 0)
            }

            for (index in s.indices) {
                left.getOrNull(index - 1)?.also {
                    left[index][0] = it[0]
                    left[index][1] = it[1]
                }

                left[index][index xor (s[index] - '0') and 1]++
            }

            return if (s.length % 2 == 0) {
                left[left.lastIndex].let { it[0].coerceAtMost(it[1]) }
            } else {
                val right = Array(s.length) {
                    intArrayOf(0, 0)
                }

                for (index in s.lastIndex - 1 downTo 0) {
                    right[index + 1].also {
                        right[index][0] = it[0]
                        right[index][1] = it[1]
                    }

                    right[index][index xor (s[index + 1] - '0') and 1]++
                }

                var result = Int.MAX_VALUE
                left.indices.forEach {
                    val (left1010, left0101) = left[it]
                    val (right1010, right0101) = right[it]

                    result = result.coerceAtMost(left0101 + right0101).coerceAtMost(left1010 + right1010)
                }

                result
            }
        }
    }

    measureTimeMillis {
        Solution().minFlips(
            "10110"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
