package p28xx

import util.expect

fun main() {
    class Solution {
        fun countSymmetricIntegers(low: Int, high: Int): Int {
            return (low..high).count {
                it.toString().takeIf { it.length % 2 == 0 }?.takeIf {
                    var l = 0
                    var r = it.lastIndex

                    var sum = 0

                    while (l < r) {
                        sum += it[l++] - '0'
                        sum -= it[r--] - '0'
                    }

                    sum == 0
                } != null
            }
        }
    }

    expect {
        Solution().countSymmetricIntegers(
            36, 14
        )
    }
}