package p06xx

import util.expect

fun main() {
    class Solution {
        fun maxDistance(arrays: List<List<Int>>): Int {
            val min = arrays.sortedBy { it[0] }
            val max = arrays.sortedByDescending { it.last() }

            return if (min[0] == max[0]) {
                maxOf(max[0].last() - min[1][0], max[1].last() - min[0][0])
            } else {
                max[0].last() - min[0][0]
            }
        }
    }

    expect {
        Solution().maxDistance(
            listOf()
        )

    }
}