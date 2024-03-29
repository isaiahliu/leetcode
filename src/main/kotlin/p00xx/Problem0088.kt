package p00xx

import util.expect

fun main() {
    class Solution {
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
            var writeIndex = m + n - 1
            var num1Index = m - 1
            var num2Index = n - 1

            while (num2Index >= 0) {
                var max = Int.MIN_VALUE
                if (num1Index >= 0) {
                    max = nums1[num1Index]
                }

                if (nums2[num2Index] > max) {
                    max = nums2[num2Index]
                    num2Index--
                } else {
                    num1Index--
                }

                nums1[writeIndex--] = max
            }
        }
    }

    expect {
        Solution().merge(
            intArrayOf(),
            1,
            intArrayOf(),
            1
        )
    }
}

