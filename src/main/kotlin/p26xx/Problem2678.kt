package p26xx

import util.expect

fun main() {
    class Solution {
        fun countSeniors(details: Array<String>): Int {
            return details.count {
                it.substring(11, 13) > "60"
            }
        }
    }

    expect {
        Solution().countSeniors(
            arrayOf()
        )
    }
}
