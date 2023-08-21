package p19xx

import util.expect

fun main() {
    class Solution {
        fun kthLargestNumber(nums: Array<String>, k: Int): String {
            nums.sortWith(compareByDescending<String> { it.length }.thenByDescending { it })

            return nums[k - 1]
        }
    }

    expect {
        Solution().kthLargestNumber(
            arrayOf(), 1
        )
    }
}