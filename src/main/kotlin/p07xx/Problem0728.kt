package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun selfDividingNumbers(left: Int, right: Int): List<Int> {
            return (left..right).filter { num ->
                num.toString().map { it - '0' }.all {
                    it > 0 && num % it == 0
                }
            }
        }
    }

    measureTimeMillis {
        Solution().selfDividingNumbers(
            1, 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}