package p02xx

import util.expect

fun main() {
    class Solution {
        fun findDuplicate(nums: IntArray): Int {
            var slow = nums[0]
            var fast = nums[nums[0]]

            while (true) {
                if (slow == fast) {
                    slow = 0

                    while (slow != fast) {
                        slow = nums[slow]
                        fast = nums[fast]
                    }

                    return slow
                }

                slow = nums[slow]
                fast = nums[nums[fast]]
            }
        }
    }

    expect {
        Solution().findDuplicate(intArrayOf(1, 3, 4, 2, 2))
    }
}

