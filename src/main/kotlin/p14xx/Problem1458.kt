package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxDotProduct(nums1: IntArray, nums2: IntArray): Int {
            val cache = hashMapOf<Pair<Pair<Int, Int>, Boolean>, Int>()

            fun dfs(index1: Int, index2: Int, picked: Boolean): Int? {
                if (index1 >= nums1.size || index2 >= nums2.size) {
                    return 0.takeIf { picked }
                }

                val cacheKey = index1 to index2 to picked
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val num1 = nums1[index1]
                val num2 = nums2[index2]

                var result = num1 * num2 + (dfs(index1 + 1, index2 + 1, true) ?: 0)
                dfs(index1 + 1, index2, picked)?.also {
                    result = result.coerceAtLeast(it)
                }

                dfs(index1, index2 + 1, picked)?.also {
                    result = result.coerceAtLeast(it)
                }

                cache[cacheKey] = result
                return result
            }

            return dfs(0, 0, false) ?: 0
        }
    }

    measureTimeMillis {
        Solution().maxDotProduct(
            intArrayOf(-1, -1), intArrayOf(1, 1)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

