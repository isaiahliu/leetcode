package p22xx

import util.expect

fun main() {
    class Solution {
        fun minimumLines(stockPrices: Array<IntArray>): Int {
            if (stockPrices.size < 3) {
                return stockPrices.size - 1
            }

            stockPrices.sortBy { it[0] }

            return (1 until stockPrices.lastIndex).count {
                val (x1, y1) = stockPrices[it - 1]
                val (x2, y2) = stockPrices[it]
                val (x3, y3) = stockPrices[it + 1]

                (y3 - y2) * (x2 - x1) != (y2 - y1) * (x3 - x2)
            } + 1
        }
    }

    expect {
        Solution().minimumLines(
            arrayOf()
        )
    }
}