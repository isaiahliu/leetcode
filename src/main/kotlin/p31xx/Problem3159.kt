package p31xx

import util.expect

fun main() {
    class Solution {
        fun occurrencesOfElement(nums: IntArray, queries: IntArray, x: Int): IntArray {
            return nums.indices.filter { nums[it] == x }.let { indices ->
                queries.map { indices.getOrNull(it - 1) ?: -1 }
            }.toIntArray()
        }
    }

    expect {
        Solution().occurrencesOfElement(
            intArrayOf(), intArrayOf(), 1
        )
    }
}
