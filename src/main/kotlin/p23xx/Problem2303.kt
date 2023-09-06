package p23xx

import util.expect

fun main() {
    class Solution {
        fun calculateTax(brackets: Array<IntArray>, income: Int): Double {
            var preUpper = 0
            var preRate = 0

            var result = 0.0
            for ((upper, rate) in brackets) {
                result += (income - preUpper) / 100.0 * (rate - preRate)

                if (income <= upper) {
                    break
                }
                preRate = rate
                preUpper = upper
            }

            return result
        }
    }

    expect {
        Solution().calculateTax(
            arrayOf(
                intArrayOf(3, 50),
                intArrayOf(7, 10),
                intArrayOf(12, 25),
            ), 10
        )
    }
}