package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
            if (k == 1) {
                return nums
            }

            val base = 10000

            val result = IntArray(nums.size - k + 1)

            val counts = IntArray(base * 2 + 1)
            val set = sortedSetOf<Int>()

            repeat(k) {
                (nums[it] + base).also {
                    set.add(it)
                    counts[it]++
                }
            }

            result[0] = set.last() - base
            for (i in 0 until nums.size - k) {
                (nums[i] + base).also {
                    counts[it]--
                    if (counts[it] == 0) {
                        set.remove(it)
                    }
                }
                (nums[i + k] + base).also {
                    set.add(it)
                    counts[it]++
                }

                result[i + 1] = set.last() - base
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3).also { println(it) }
    }
}

