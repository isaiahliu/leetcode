package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSumOfThreeSubarrays(nums: IntArray, k: Int): IntArray {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }

            val cache1 = hashMapOf<Int, Pair<Int, Int>>()
            fun find1(startIndex: Int): Pair<Int, Int>? {
                if (startIndex >= nums.size) {
                    return null
                }

                if (startIndex in cache1) {
                    return cache1[startIndex]
                }

                var max = nums[startIndex] - (nums.getOrNull(startIndex - k) ?: 0)
                var idx = startIndex - k + 1

                find1(startIndex + 1)?.also { (s, i) ->
                    if (s > max) {
                        max = s
                        idx = i
                    }
                }

                cache1[startIndex] = max to idx

                return max to idx
            }

            val cache2 = hashMapOf<Int, Pair<Int, Pair<Int, Int>>>()
            fun find2(startIndex: Int): Pair<Int, Pair<Int, Int>>? {
                if (startIndex >= nums.size) {
                    return null
                }

                if (startIndex in cache2) {
                    return cache2[startIndex]
                }

                val (nextMax, nextIdx) = find1(startIndex + k) ?: return null
                var max = nums[startIndex] - (nums.getOrNull(startIndex - k) ?: 0) + nextMax
                var idx = startIndex - k + 1 to nextIdx

                find2(startIndex + 1)?.also { (s, i) ->
                    if (s > max) {
                        max = s
                        idx = i
                    }
                }

                cache2[startIndex] = max to idx

                return max to idx
            }

            val (c, p) = find2(k * 2 - 1)!!
            var max = nums[k - 1] + c
            val idx = intArrayOf(0, p.first, p.second)

            for (i in k until nums.size) {
                val firstSum = nums[i] - nums[i - k]

                val (count, second) = find2(i + k) ?: continue

                if (firstSum + count > max) {
                    max = firstSum + count
                    idx[0] = i - k + 1
                    idx[1] = second.first
                    idx[2] = second.second
                }
            }

            return idx
        }
    }

    measureTimeMillis {
        Solution().maxSumOfThreeSubarrays(
            intArrayOf(1, 2, 1, 2, 6, 7, 5, 1), 2
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}