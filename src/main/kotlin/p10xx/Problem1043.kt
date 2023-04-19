package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSumAfterPartitioning(arr: IntArray, k: Int): Int {
            if (k == 1) {
                return arr.sum()
            }

            if (k >= arr.size) {
                return arr.max() * arr.size
            }

            val dp = IntArray(arr.size)

            repeat(dp.size) { index ->
                var max = 0
                for (i in index downTo (index - k + 1).coerceAtLeast(0)) {
                    max = max.coerceAtLeast(arr[i])

                    dp[index] = dp[index].coerceAtLeast(max * (index - i + 1) + (dp.getOrNull(i - 1) ?: 0))
                }
            }

            return dp[dp.lastIndex]
        }

        fun maxSumAfterPartitioning2(arr: IntArray, k: Int): Int {
            if (k == 1) {
                return arr.sum()
            }

            if (k >= arr.size) {
                return arr.max() * arr.size
            }

            val cache = hashMapOf<Int, Int>()
            fun find(startIndex: Int): Int {
                if (startIndex >= arr.size) {
                    return 0
                }

                if (startIndex in cache) {
                    return cache[startIndex] ?: 0
                }

                var result = 0
                var max = -1
                for (count in 1..k) {
                    max = max.coerceAtLeast(arr.getOrNull(startIndex + count - 1) ?: break)

                    result = result.coerceAtLeast(max * count + find(startIndex + count))
                }

                cache[startIndex] = result
                return result
            }

            return find(0)
        }
    }
    measureTimeMillis {
        Solution().maxSumAfterPartitioning(
            intArrayOf(1, 15, 7, 9, 2, 5, 10), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
