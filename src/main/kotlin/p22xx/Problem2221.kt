package p22xx

import util.expect
import java.math.BigInteger

fun main() {
    class Solution {
        fun triangularSum(nums: IntArray): Int {
            var result = 0
            var mul = BigInteger.ONE
            nums.forEachIndexed { index, i ->
                result += i * mul.mod(10.toBigInteger()).toInt()
                result %= 10

                mul *= (nums.size - index - 1).toBigInteger()
                mul /= (index + 1).toBigInteger()
            }

            return result
        }
    }

    expect {
        Solution().triangularSum(
            intArrayOf(1, 2, 3, 4, 5)
        )
    }
}