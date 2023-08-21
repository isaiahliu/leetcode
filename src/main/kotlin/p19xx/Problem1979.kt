package p19xx

import util.expect

fun main() {
    class Solution {
        fun findGCD(nums: IntArray): Int {
            fun Int.gcd(target: Int): Int {
                if (this % target == 0) {
                    return target
                }

                return target.gcd(this % target)
            }

            return nums.max().gcd(nums.min())
        }
    }

    expect {
        Solution().findGCD(
            intArrayOf()
        )
    }
}