package p25xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumTastiness(price: IntArray, k: Int): Int {
            price.sort()

            fun binarySearch(min: Int, max: Int): Int {
                if (min > max) {
                    return 0
                }

                val mid = (min + max) / 2

                var result = 1
                var pre = price[0]

                for (i in 1 until price.size) {
                    val p = price[i]

                    if (p - pre >= mid) {
                        result++
                        pre = p
                    }
                }

                return if (result < k) {
                    binarySearch(min, mid - 1)
                } else {
                    mid.coerceAtLeast(binarySearch(mid + 1, max))
                }
            }

            return binarySearch(0, price[price.lastIndex] - price[0])
        }
    }

    measureTimeMillis {
        Solution().maximumTastiness(
            intArrayOf(13, 5, 1, 8, 21, 2), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
