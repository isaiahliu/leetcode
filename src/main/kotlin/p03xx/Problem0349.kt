package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
            val set = nums1.toMutableSet()
            val result = hashSetOf<Int>()
            nums2.forEach {
                if (it in set) {
                    result.add(it)
                }
            }
            return result.toIntArray()
        }
    }
    measureTimeMillis {
        Solution().intersection(
            intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

