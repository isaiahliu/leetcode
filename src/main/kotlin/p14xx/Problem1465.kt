package p14xx

import util.expect

fun main() {
    class Solution {
        fun maxArea(h: Int, w: Int, horizontalCuts: IntArray, verticalCuts: IntArray): Int {
            fun IntArray.maxRange(m: Int): Int {
                val sorted = sorted()
                var result = m - sorted[lastIndex]

                var pre = 0

                sorted.forEach {
                    result = result.coerceAtLeast(it - pre)

                    pre = it
                }

                return result
            }

            return (1L * horizontalCuts.maxRange(h) * verticalCuts.maxRange(w) % 1000000007).toInt()
        }
    }

    expect {
        Solution().maxArea(
            5, 4, intArrayOf(3, 1), intArrayOf(1)
        )

    }
}

