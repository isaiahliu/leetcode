package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kConcatenationMaxSum(arr: IntArray, k: Int): Int {
            var result = 0L
            var t = 0L
            arr.forEach {
                t += it

                t = t.coerceAtLeast(0)
                result = result.coerceAtLeast(t)
            }

            if (result == 0L) {
                return 0
            }

            val m = 1000000007
            if (k == 1) {
                return (result % m).toInt()
            }

            var preSum = 0L
            var postSum = 0L
            var sum = 0L
            var post = 0L
            for (i in arr.indices) {
                sum += arr[i]
                preSum = preSum.coerceAtLeast(sum)
                post += arr[arr.lastIndex - i]
                postSum = postSum.coerceAtLeast(post)
            }

            result = result.coerceAtLeast(preSum + postSum + sum.coerceAtLeast(0) * (k - 2))

            return (result % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().kConcatenationMaxSum(
            intArrayOf(-9, 13, 4, -16, -12, -16, 3, -7, 5, -16, 16, 8, -1, -13, 15, 3), 6
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}