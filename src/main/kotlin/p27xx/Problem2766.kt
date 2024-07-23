package p27xx

import util.expect

fun main() {
    class Solution {
        fun relocateMarbles(nums: IntArray, moveFrom: IntArray, moveTo: IntArray): List<Int> {
            val result = nums.toSortedSet()

            moveFrom.indices.forEach {
                result -= moveFrom[it]
                result += moveTo[it]
            }

            return result.toList()
        }
    }

    expect {
        Solution().relocateMarbles(
            intArrayOf(), intArrayOf(), intArrayOf()
        )
    }
}
