package p15xx

import util.expect

fun main() {
    class Solution {
        fun maxSum(nums1: IntArray, nums2: IntArray): Int {
            val map = hashMapOf<Int, IntArray>()

            nums1.forEachIndexed { index, i ->
                map.computeIfAbsent(i) { intArrayOf(nums1.size, nums2.size) }[0] = index
            }

            nums2.forEachIndexed { index, i ->
                map.computeIfAbsent(i) { intArrayOf(nums1.size, nums2.size) }[1] = index
            }

            val cache = hashMapOf<Int, Long>()
            fun dfs(num: Int): Long {
                if (num in cache) {
                    return cache[num] ?: 0L
                }

                var result = 0L
                map[num]?.also { (index1, index2) ->
                    nums1.getOrNull(index1 + 1)?.also {
                        result = result.coerceAtLeast(dfs(it))
                    }

                    nums2.getOrNull(index2 + 1)?.also {
                        result = result.coerceAtLeast(dfs(it))
                    }
                }

                result += num
                cache[num] = result

                return result
            }

            return (dfs(nums1[0]).coerceAtLeast(dfs(nums2[0])) % 1000000007).toInt()
        }
    }

    expect {
        Solution().maxSum(
            intArrayOf(), intArrayOf()
        )
    }
}

