package p23xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray, numsDivide: IntArray): Int {
            fun gcd(num1: Int, num2: Int): Int {
                return if (num1 < num2) {
                    gcd(num2, num1)
                } else if (num1 % num2 == 0) {
                    num2
                } else {
                    gcd(num2, num1 % num2)
                }
            }

            val gcd = numsDivide.reduce { a, b -> gcd(a, b) }

            nums.sort()

            nums.forEachIndexed { index, i ->
                when {
                    gcd % i == 0 -> return index
                    i > gcd -> return -1
                }
            }

            return -1
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(18, 43, 36, 13, 7), intArrayOf()
        )
    }
}