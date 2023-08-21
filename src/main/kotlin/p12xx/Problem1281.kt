package p12xx

import util.expect

fun main() {
    class Solution {
        fun subtractProductAndSum(n: Int): Int {
            var add = 0
            var mul = 1

            var t = n
            while (t > 0) {
                (t % 10).also {
                    add += it
                    mul *= it
                }

                t /= 10
            }

            return mul - add
        }
    }

    expect {
        Solution().subtractProductAndSum(
            1234
        )
    }
}
