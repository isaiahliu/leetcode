package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumWealth(accounts: Array<IntArray>): Int {
            return accounts.map { it.sum() }.max()
        }
    }

    measureTimeMillis {
        Solution().maximumWealth(
            arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

