package p24xx

import util.expect

fun main() {
    class Solution {
        fun subarrayGCD(nums: IntArray, k: Int): Int {
            fun gcd(num1: Int, num2: Int): Int {
                return when {
                    num1 % num2 == 0 -> num2
                    else -> gcd(num2, num1 % num2)
                }
            }

            var result = 0
            for (i in nums.indices) {
                var gcd = nums[i]

                if (gcd == k) {
                    result++
                }

                for (j in i + 1 until nums.size) {
                    gcd = gcd(gcd, nums[j])

                    when {
                        gcd < k -> break
                        gcd == k -> result++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().subarrayGCD(
            intArrayOf(1, 3, 5, 2, 7, 5), 1
        )
    }
}