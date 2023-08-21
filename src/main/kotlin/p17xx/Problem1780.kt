package p17xx

import util.expect

fun main() {
    class Solution {
        fun checkPowersOfThree(n: Int): Boolean {
            var t = n
            while (t > 0) {
                when (t % 3) {
                    0 -> {
                        t /= 3
                    }

                    1 -> {
                        t = (t - 1) / 3
                    }

                    else -> {
                        return false
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().checkPowersOfThree(
            91
        )
    }
}
