package p22xx

import util.expect

fun main() {
    class Solution {
        fun intersection(nums: Array<IntArray>): List<Int> {
            return nums.map { it.toSet() }.reduce { a, b -> a.intersect(b) }.sorted()
        }
    }

    expect {
        Solution().intersection(
            arrayOf()
        )
    }
}