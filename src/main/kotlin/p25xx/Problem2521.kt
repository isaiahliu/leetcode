package p25xx

import util.expect

fun main() {
    class Solution {
        val factors = arrayListOf<Int>()
        val nums = hashSetOf<Int>()

        init {
            for (num in 2..1000) {
                if (num !in nums) {
                    factors += num

                    var t = num * 2
                    while (t <= 1000) {
                        nums += t
                        t += num
                    }
                }
            }
        }

        fun distinctPrimeFactors(nums: IntArray): Int {
            val result = hashSetOf<Int>()

            nums.forEach {
                var t = it
                var factorIndex = 0

                while (t > 1) {
                    if (t % factors[factorIndex] == 0) {
                        result += factors[factorIndex]
                        t /= factors[factorIndex]
                    } else {
                        factorIndex++
                    }
                }
            }

            return result.size
        }
    }

    expect {
        Solution().distinctPrimeFactors(
            intArrayOf()
        )
    }
}