package p02xx

import util.expect

fun main() {
    class Solution {
        fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
            if (k == 0) {
                return false
            }

            val set = hashSetOf<Int>()
            repeat((k + 1).coerceAtMost(nums.size)) {

                if (!set.add(nums[it])) {
                    return true
                }
            }

            var leftIndex = 0
            var rightIndex = k + 1

            while (rightIndex < nums.size) {
                set.remove(nums[leftIndex++])

                if (!set.add(nums[rightIndex++])) {
                    return true
                }
            }
            return false
        }
    }

    expect {
        Solution().containsNearbyDuplicate(
            intArrayOf(1, 0, 1, 1), 1
        )
    }
}

