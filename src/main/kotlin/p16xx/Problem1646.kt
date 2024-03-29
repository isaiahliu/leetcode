package p16xx

import util.expect

fun main() {
    class Solution {
        fun getMaximumGenerated(n: Int): Int {
            var result = n.coerceAtMost(1)

            val nums = IntArray(n + 1) { it }

            for (i in 2..n) {
                var num = nums[i / 2]

                if (i % 2 == 1) {
                    num += nums[i / 2 + 1]
                }

                result = result.coerceAtLeast(num)

                nums[i] = num
            }

            return result
        }
    }

    expect {
        Solution().getMaximumGenerated(
            7
        )
    }
}