package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
            val map12 = hashMapOf<Int, Int>()
            val map34 = hashMapOf<Int, Int>()

            nums1.forEach { n1 ->
                nums2.forEach { n2 ->
                    map12[n1 + n2] = (map12[n1 + n2] ?: 0) + 1
                }
            }

            nums3.forEach { n3 ->
                nums4.forEach { n4 ->
                    map34[n3 + n4] = (map34[n3 + n4] ?: 0) + 1
                }
            }

            var result = 0

            map12.forEach { (n, count) ->
                map34[-n]?.also {
                    result += count * it
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().fourSumCount(intArrayOf(), intArrayOf(), intArrayOf(), intArrayOf()).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}