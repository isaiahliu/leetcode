package p23xx

import util.expect

fun main() {
    class Solution {
        fun smallestTrimmedNumbers(nums: Array<String>, queries: Array<IntArray>): IntArray {
            return queries.map { (k, trim) ->
                nums.indices.sortedBy {
                    nums[it].takeLast(trim)
                }[k - 1]
            }.toIntArray()
        }
    }

    expect {
        Solution().smallestTrimmedNumbers(
            arrayOf(), arrayOf()
        )
    }
}