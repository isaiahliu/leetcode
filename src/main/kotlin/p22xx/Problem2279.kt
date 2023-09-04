package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumBags(capacity: IntArray, rocks: IntArray, additionalRocks: Int): Int {
            var remain = additionalRocks.toLong()
            return capacity.indices.sortedBy { capacity[it] - rocks[it] }.count {
                remain -= capacity[it] - rocks[it]
                remain >= 0
            }
        }
    }

    expect {
        Solution().maximumBags(
            intArrayOf(), intArrayOf(), 0
        )
    }
}