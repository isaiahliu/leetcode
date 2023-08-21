package p06xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxSumOfThreeSubarrays(nums: IntArray, k: Int): IntArray {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }

            val map1 = TreeMap<Int, Int>()

            for (i in 0..nums.size - k) {
                val num = nums[i + k - 1] - (nums.getOrNull(i - 1) ?: 0)

                while (true) {
                    map1.lowerEntry(i)?.takeIf { it.value < num }?.key?.also {
                        map1.remove(it)
                    } ?: break
                }

                map1[i] = num
            }

            val map2 = TreeMap<Int, Pair<Int, Int>>()

            for (i in 0..nums.size - k * 2) {
                val next = map1.higherEntry(i + k - 1) ?: break
                val num = nums[i + k - 1] - (nums.getOrNull(i - 1) ?: 0) + next.value

                while (true) {
                    map2.lowerEntry(i)?.takeIf { it.value.first < num }?.key?.also {
                        map2.remove(it)
                    } ?: break
                }

                map2[i] = num to next.key
            }

            var max = 0
            val result = intArrayOf(0, 0, 0)

            for (i in 0..nums.size - k * 3) {
                val next = map2.higherEntry(i + k - 1) ?: break
                val num = nums[i + k - 1] - (nums.getOrNull(i - 1) ?: 0) + next.value.first

                if (num > max) {
                    max = num
                    result[0] = i
                    result[1] = next.key
                    result[2] = next.value.second
                }
            }

            return result
        }
    }

    expect {
        Solution().maxSumOfThreeSubarrays(
            intArrayOf(1, 2, 1, 2, 6, 7, 5, 1), 2
        ).toList()
    }
}