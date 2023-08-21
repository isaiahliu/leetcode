package p17xx

import util.expect

fun main() {
    class Solution {
        fun checkOnesSegment(s: String): Boolean {
            var status = s[0] - '0'
            s.forEach {
                status += (status xor (it - '0')) and 1

                if (status == 3) {
                    return false
                }
            }
            return true
        }
    }

    expect {
        Solution().checkOnesSegment(
            "11001"
        )
    }
}
