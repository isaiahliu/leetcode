package p26xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kItemsWithMaximumSum(numOnes: Int, numZeros: Int, numNegOnes: Int, k: Int): Int {
            var result = 0

            var r = k
            var num = 1
            for (count in arrayOf(numOnes, numZeros, numNegOnes)) {
                if (r == 0) {
                    break
                }

                r.coerceAtMost(count).also {
                    result += num * it
                    r -= it
                }

                num--
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().kItemsWithMaximumSum(
            3, 2, 0, 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
