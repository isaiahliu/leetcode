package p26xx

import util.expect

fun main() {
    class Solution {
        fun sumOfPower(nums: IntArray): Int {
            nums.sort()

            val m = 1000000007

            var result = 0L

            var minSum = 0L
            nums.forEach { num ->
                result += ((num.toLong() * num) % m) * (minSum + num)
                result %= m

                minSum = (minSum * 2 + num) % m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().sumOfPower(
            intArrayOf(1, 2, 4),
        )
    }
}
