package p16xx

import java.math.BigInteger
import util.expect

fun main() {
    class Solution {
        val cache = hashMapOf<Pair<Int, Int>, Int>()
        fun p(row: Int, column: Int): Int {
            if (row == 0 || column == 0) {
                return 1
            }

            val cacheKey = row.coerceAtMost(column) to row.coerceAtLeast(column)
            if (cacheKey in cache) {
                return cache[cacheKey] ?: 0
            }

            var result = BigInteger.ONE

            repeat(row + column) {
                result *= (it + 1).toBigInteger()
            }

            repeat(row) {
                result /= (it + 1).toBigInteger()
            }

            repeat(column) {
                result /= (it + 1).toBigInteger()
            }

            cache[cacheKey] = result.toInt()
            return result.toInt()
        }

        fun kthSmallestPath(destination: IntArray, k: Int): String {
            val (r, c) = destination

            return if (r == 0) {
                "H".repeat(c)
            } else if (c == 0) {
                "V".repeat(r)
            } else {
                val horizontalFirst = p(r, c - 1)
                if (horizontalFirst >= k) {
                    "H" + kthSmallestPath(intArrayOf(r, c - 1), k)
                } else {
                    "V" + kthSmallestPath(intArrayOf(r - 1, c), k - horizontalFirst)
                }
            }
        }
    }

    expect {
        Solution().kthSmallestPath(
            intArrayOf(15, 15), 1
        )
    }
}