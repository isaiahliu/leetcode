package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kthGrammar(n: Int, k: Int): Int {
            var t = k - 1

            var result = 0
            repeat(n - 1) {
                result = t % 2 xor result
                t /= 2
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().kthGrammar(
            3, 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}