package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
            var writeIndex = m + n - 1
            var num1Index = m - 1
            var num2Index = n - 1

            while (writeIndex >= 0) {
                var max = Int.MIN_VALUE
                if (num1Index >= 0) {
                    max = nums1[num1Index]
                }

                if (num2Index >= 0 && nums2[num2Index] > max) {
                    max = nums2[num2Index]
                    num2Index--
                } else {
                    num1Index--
                }

                nums1[writeIndex--] = max
            }
        }
    }

    measureTimeMillis {
        Solution().merge(
            intArrayOf(),
            1,
            intArrayOf(),
            1
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

