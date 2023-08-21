package p15xx

import util.expect

fun main() {
    class Solution {
        fun minFlips(target: String): Int {
            var pre = '0'

            var result = 0
            target.forEach {
                if (pre != it) {
                    result++
                    pre = '0' + ('1' - pre)
                }
            }

            return result
        }
    }

    expect {
        Solution().minFlips(
            "10111"
        )
    }
}

