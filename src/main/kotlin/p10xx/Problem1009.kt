package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun bitwiseComplement(n: Int): Int {
            return n.toString(2).map {
                '1' - (it - '0')
            }.toCharArray().let { String(it) }.toInt(2)
        }
    }

    measureTimeMillis {
        Solution().bitwiseComplement(
            12
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
