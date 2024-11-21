package p32xx

import util.expect
import kotlin.math.sqrt

fun main() {
    class Solution {
        fun nonSpecialCount(l: Int, r: Int): Int {
            val prime = BooleanArray(sqrt(r.toDouble()).toInt() + 1) { true }

            var result = r - l + 1
            for (num in 2 until prime.size) {
                if (prime[num]) {
                    if (num * num >= l) {
                        result--
                    }

                    for (quarter in num * 2 until prime.size step num) {
                        prime[quarter] = false
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().nonSpecialCount(
            5, 7
        )
    }
}
