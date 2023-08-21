package p03xx

import util.expect

fun main() {
    class Solution {
        fun getMoneyAmount(n: Int): Int {
            if (n <= 2) {
                return n - 1
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun find(left: Int, right: Int): Int {
                when {
                    left >= right -> return 0
                    right - left == 1 -> return left
                    right - left == 2 -> return left + 1
                }

                val cacheKey = left to right
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var min = Int.MAX_VALUE

                for (mid in left + 1 until right) {
                    val leftSum = find(left, mid - 1)
                    val rightSum = find(mid + 1, right)

                    val sum = mid + leftSum.coerceAtLeast(rightSum)

                    min = min.coerceAtMost(sum)
                }

                cache[cacheKey] = min

                return min
            }

            return find(1, n)
        }
    }

    expect {
        Solution().getMoneyAmount(
            10
        )
    }
}

