package p28xx

import util.expect

fun main() {
    class Solution {
        fun countWays(nums: List<Int>): Int {
            var result = 0

            var pre = -1
            var count = 0
            (nums + Int.MAX_VALUE).sortedBy { it }.forEach { num ->
                if (num > pre && count in (pre + 1) until num) {
                    result++
                }
                count++
                pre = num
            }

            return result
        }
    }

    expect {
        Solution().countWays(
            listOf(1, 1)
        )
    }
}
