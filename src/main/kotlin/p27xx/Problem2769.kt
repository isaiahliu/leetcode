package p27xx

import util.expect

fun main() {
    class Solution {
        fun theMaximumAchievableX(num: Int, t: Int): Int {
            return num + t * 2
        }
    }

    expect {
        Solution().theMaximumAchievableX(
            5, 4
        )
    }
}
