package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun areAlmostEqual(s1: String, s2: String): Boolean {
            val diff = s1.indices.filter { s1[it] != s2[it] }

            return when (diff.size) {
                0 -> true
                2 -> s1[diff[0]] == s2[diff[1]] && s1[diff[1]] == s2[diff[0]]
                else -> false
            }
        }
    }

    measureTimeMillis {
        Solution().areAlmostEqual(
            "bank", "kanb"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
