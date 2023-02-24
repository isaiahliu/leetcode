package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
            if (k == 1) {
                return nums
            }

            val result = IntArray(nums.size - k + 1)

            val map = sortedMapOf<Int, Int>()

            repeat(k) {
                nums[it].also {
                    map[it] = (map[it] ?: 0) + 1
                }
            }

            result[0] = map.lastKey()

            for (i in 0 until nums.size - k) {
                nums[i].also {
                    if (map[it] == 1) {
                        map.remove(it)
                    } else {
                        map[it] = (map[it] ?: 0) - 1
                    }
                }
                nums[i + k].also {
                    map[it] = (map[it] ?: 0) + 1
                }

                result[i + 1] = map.lastKey()
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3).also { println(it) }
    }
}

