package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumRemovals(s: String, p: String, removable: IntArray): Int {
            val removeIndex = IntArray(s.length) { Int.MAX_VALUE }

            removable.forEachIndexed { index, i ->
                removeIndex[i] = index
            }

            fun binarySearch(min: Int, max: Int): Int? {
                if (min > max) {
                    return null
                }

                val mid = (min + max) / 2

                var sIndex = 0
                var pIndex = 0
                while (sIndex < s.length && pIndex < p.length) {
                    if (removeIndex[sIndex] >= mid && s[sIndex] == p[pIndex]) {
                        pIndex++
                    }

                    sIndex++
                }

                return if (pIndex == p.length) {
                    binarySearch(mid + 1, max) ?: mid
                } else {
                    binarySearch(min, mid - 1)
                }
            }

            return binarySearch(1, removable.size) ?: 0
        }
    }

    measureTimeMillis {
        Solution().maximumRemovals(
            "", "", intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
