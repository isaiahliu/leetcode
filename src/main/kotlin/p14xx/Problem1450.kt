package p14xx

import util.expect

fun main() {
    class Solution {
        fun busyStudent(startTime: IntArray, endTime: IntArray, queryTime: Int): Int {
            return startTime.indices.count {
                queryTime in startTime[it]..endTime[it]
            }
        }
    }

    expect {
        Solution().busyStudent(
            intArrayOf(1, 2, 3), intArrayOf(3, 2, 7), 4
        )

    }
}

