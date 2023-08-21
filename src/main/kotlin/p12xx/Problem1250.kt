package p12xx

import java.math.BigInteger
import util.expect

fun main() {
    class Solution {
        fun isGoodArray(nums: IntArray): Boolean {
            var gcd = nums[0].toBigInteger()

            for (i in 1 until nums.size) {
                gcd = gcd.gcd(nums[i].toBigInteger())

                if (gcd.compareTo(BigInteger.ONE) == 0) {
                    return true
                }
            }

            return gcd.compareTo(BigInteger.ONE) == 0
        }
    }

    expect {
        Solution().isGoodArray(intArrayOf(1, 2, 3))
    }
}

