package p33xx

import util.expect

fun main() {
    class Solution {
        fun lengthAfterTransformations(s: String, t: Int): Int {
            val m = 1000000007L

            var dp = LongArray(26)
            s.forEach {
                dp[it - 'a']++
            }

            repeat(t) {
                dp = LongArray(26) { index ->
                    var count = dp[(index - 1).mod(26)]

                    if (index == 1) {
                        count += dp[25]
                    }

                    count % m
                }
            }

            return (dp.sum() % m).toInt()
        }
    }

    expect {
        Solution().lengthAfterTransformations(
            "aeouih", 0
        )
    }
}
