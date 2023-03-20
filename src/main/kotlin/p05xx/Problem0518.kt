package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun change(amount: Int, coins: IntArray): Int {
            coins.sortDescending()

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun find(startIndex: Int, num: Int): Int {
                if (num == 0) {
                    return 1
                }

                if (startIndex > coins.lastIndex || num < 0) {
                    return 0
                }

                val cacheKey = startIndex to num

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val result = find(startIndex + 1, num) + find(startIndex, num - coins[startIndex])

                cache[cacheKey] = result
                return result
            }

            return find(0, amount)
        }
    }

    measureTimeMillis {
        Solution().change(
            5, intArrayOf(5, 2, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}