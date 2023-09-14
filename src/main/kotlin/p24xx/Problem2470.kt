package p24xx

import util.expect

fun main() {
    class Solution {
        fun subarrayLCM(nums: IntArray, k: Int): Int {
            fun gcd(num1: Int, num2: Int): Int {
                return if (num1 % num2 == 0) {
                    num2
                } else {
                    gcd(num2, num1 % num2)
                }
            }

            var result = 0
            for (i in nums.indices) {
                if (k % nums[i] != 0) {
                    continue
                }

                var gcd = k / nums[i]
                if (gcd == 1) {
                    result++
                }

                for (j in i + 1 until nums.size) {
                    if (k % nums[j] != 0) {
                        break
                    }

                    gcd = gcd(gcd, k / nums[j])
                    if (gcd == 1) {
                        result++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().subarrayLCM(
            intArrayOf(3, 6, 2, 7, 1), 6
        )
    }
}