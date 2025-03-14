package p25xx

import util.expect

fun main() {
    class Solution {
        fun beautifulSubarrays(nums: IntArray): Long {
            var sum = 0
            val sums = hashMapOf<Int, Int>(0 to 1)

            var result = 0L
            nums.forEach {
                sum = sum xor it

                sums[sum]?.also {
                    result += it
                }

                sums[sum] = (sums[sum] ?: 0) + 1
            }

            return result
        }
    }

    expect {
        Solution().beautifulSubarrays(intArrayOf())
    }
}