package p07xx

import util.expect

fun main() {
    class Solution {
        fun rotateString(s: String, goal: String): Boolean {
            return s.length == goal.length && s in "${goal}${goal}"
        }
    }

    expect {
        Solution().rotateString(
            "", ""
        )
    }
}