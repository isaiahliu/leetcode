package p30xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumCost(nums: IntArray, k: Int, dist: Int): Long {
            val sumCounts = TreeMap<Int, Int>()
            val maxCounts = TreeMap<Int, Int>()

            var sum = 0L
            var sumSize = 0
            fun TreeMap<Int, Int>.inc(key: Int) {
                this[key] = (this[key] ?: 0) + 1

                if (this === sumCounts) {
                    sum += key
                    sumSize++
                }
            }

            fun TreeMap<Int, Int>.dec(key: Int): Boolean {
                return this[key]?.let {
                    if (it == 1) {
                        this.remove(key)
                    } else {
                        this[key] = it - 1
                    }

                    if (this === sumCounts) {
                        sum -= key
                        sumSize--
                    }

                    true
                } ?: false
            }

            repeat(dist) {
                sumCounts.inc(nums[it + 1])
            }

            while (sumSize > k - 2) {
                sumCounts.lastKey().also {
                    sumCounts.dec(it)
                    maxCounts.inc(it)
                }
            }

            var result = Long.MAX_VALUE

            for (index in 1..nums.size - k + 1) {
                if (!sumCounts.dec(nums[index])) {
                    maxCounts.dec(nums[index])

                    sumCounts.lastEntry()?.key?.also {
                        sumCounts.dec(it)
                        maxCounts.inc(it)
                    }
                }

                nums.getOrNull(index + dist)?.also {
                    maxCounts.inc(it)
                }

                repeat(k - 2 - sumSize) {
                    maxCounts.firstKey().also {
                        maxCounts.dec(it)
                        sumCounts.inc(it)
                    }
                }

                result = minOf(result, sum + nums[index])
            }

            return result + nums[0]
        }
    }

    expect {
        Solution().minimumCost(
            intArrayOf(6, 40, 41, 11, 50, 15, 47, 48, 50, 12, 16, 30, 38, 45, 13, 34, 26, 25, 32, 28), 9, 13
        )
    }
}
