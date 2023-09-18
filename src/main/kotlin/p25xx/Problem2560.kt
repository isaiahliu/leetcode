package p25xx

import util.expect

fun main() {
    class Solution {
        fun minCapability(nums: IntArray, k: Int): Int {
            var min = nums.min()
            var max = nums.max()

            var result = 0

            while (min <= max) {
                val mid = min + (max - min) / 2

                var index = 0
                var matchCount = 0

                while (index < nums.size && matchCount < k) {
                    if (nums[index++] <= mid) {
                        matchCount++
                        index++
                    }
                }

                if (matchCount == k) {
                    result = mid
                    max = mid - 1
                } else {
                    min = mid + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().minCapability(
            intArrayOf(), 1
        )
    }
}
