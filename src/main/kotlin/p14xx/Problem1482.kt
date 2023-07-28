package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
            val max by lazy { bloomDay.max() }
            return when {
                m.toLong() * k > bloomDay.size -> {
                    -1
                }

                m.toLong() * k == bloomDay.size.toLong() -> {
                    max
                }

                else -> {
                    fun binarySearch(start: Int, end: Int): Int {
                        if (start > end) {
                            return Int.MAX_VALUE
                        }

                        val mid = (start + end) / 2

                        var count = 0
                        var remain = m
                        for (i in bloomDay.indices) {
                            if ((bloomDay.lastIndex - i) < (remain - 1) * k) {
                                return binarySearch(mid + 1, end)
                            }

                            if (bloomDay[i] <= mid) {
                                count++
                                if (count == k) {
                                    count = 0
                                    remain--

                                    if (remain == 0) {
                                        break
                                    }
                                }
                            } else {
                                count = 0
                            }
                        }

                        return if (remain == 0) {
                            mid.coerceAtMost(binarySearch(start, mid - 1))
                        } else {
                            binarySearch(mid + 1, end)
                        }
                    }

                    binarySearch(1, max)
                }
            }
        }
    }

    measureTimeMillis {
        Solution().minDays(
            intArrayOf(5, 5, 4), 1, 1
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

