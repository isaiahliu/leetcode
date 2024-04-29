package p27xx

import util.expect

fun main() {
    class Solution {
        fun numberOfEmployeesWhoMetTarget(hours: IntArray, target: Int): Int {
            return hours.count { it >= target }
        }
    }

    expect {
        Solution().numberOfEmployeesWhoMetTarget(
            intArrayOf(), 3
        )
    }
}
