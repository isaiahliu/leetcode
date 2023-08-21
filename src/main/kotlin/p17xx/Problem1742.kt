package p17xx

import util.expect

fun main() {
    class Solution {
        fun countBalls(lowLimit: Int, highLimit: Int): Int {
            return (lowLimit..highLimit).groupingBy {
                var t = it
                var r = 0
                while (t > 0) {
                    r += t % 10
                    t /= 10
                }

                r
            }.eachCount().values.max()
        }
    }

    expect {
        Solution().countBalls(
            5, 15
        )
    }
}
