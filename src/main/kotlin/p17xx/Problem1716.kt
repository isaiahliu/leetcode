package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun totalMoney(n: Int): Int {
            var result = 0

            var init = 0
            var cur = 0

            repeat(n) {
                if (it % 7 == 0) {
                    init++
                    cur = init
                } else {
                    cur++
                }

                result += cur
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().totalMoney(
            10
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
