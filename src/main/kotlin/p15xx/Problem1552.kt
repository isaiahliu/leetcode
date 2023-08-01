package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxDistance(position: IntArray, m: Int): Int {
            position.sort()

            fun binarySearch(min: Int, max: Int): Int {
                if (min > max) {
                    return Int.MIN_VALUE
                }

                val mid = (min + max) / 2

                var count = 1
                var last = position[0]
                for (p in position) {
                    if (p - last >= mid) {
                        count++
                        last = p

                        if (count >= m) {
                            break
                        }
                    }
                }

                return if (count >= m) {
                    mid.coerceAtLeast(binarySearch(mid + 1, max))
                } else {
                    binarySearch(min, mid - 1)
                }
            }

            return binarySearch(1, position[position.lastIndex] - position[0])
        }
    }

    measureTimeMillis {
        Solution().maxDistance(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4
        ).also { println(it) }
    }
}

