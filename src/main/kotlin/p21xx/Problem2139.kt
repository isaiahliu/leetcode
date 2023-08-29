package p21xx

import util.expect

fun main() {
    class Solution {
        fun minMoves(target: Int, maxDoubles: Int): Int {
            var t = target

            var result = 0
            var remain = maxDoubles
            while (t > 1 && remain > 0) {
                if (t % 2 == 1) {
                    t--
                } else {
                    t /= 2
                    remain--
                }
                result++
            }

            return result + t - 1
        }
    }

    expect {
        Solution().minMoves(
            5, 1
        )
    }
}