package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun makeArrayIncreasing(arr1: IntArray, arr2: IntArray): Int {
            val MAX = 999999
            val set = TreeSet<Int>()
            set.addAll(arr2.toSet())

            val failedNums = IntArray(arr1.size) { Int.MAX_VALUE }
            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun find(num: Int, index: Int): Int {
                if (index == arr1.size) {
                    return 0
                }

                if (num >= failedNums[index]) {
                    return MAX
                }

                val cacheKey = num to index
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: MAX
                }

                var result = MAX

                if (arr1[index] > num) {
                    result = find(arr1[index], index + 1)
                }

                set.higher(num)?.also {
                    if (it > num && (arr1[index] <= num || it < arr1[index])) {
                        result = result.coerceAtMost(find(it, index + 1) + 1)
                    }
                }

                if (result < MAX) {
                    cache[cacheKey] = result
                } else {
                    failedNums[index] = failedNums[index].coerceAtMost(num)
                }

                return result
            }

            return find(-1, 0).takeIf { it < MAX } ?: -1
        }
    }

    measureTimeMillis {
        Solution().makeArrayIncreasing(
            intArrayOf(1, 5, 3, 6, 7), intArrayOf(4, 3, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}