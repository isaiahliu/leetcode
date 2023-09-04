package p22xx

import util.expect

fun main() {
    class Solution {
        fun countDistinct(nums: IntArray, k: Int, p: Int): Int {
            var left = 0
            var right = 0

            var totalCount = 0
            val result = hashSetOf<String>()
            while (left < nums.size) {
                while (right < nums.size && (totalCount < k || nums[right] % p > 0)) {
                    if (nums[right++] % p == 0) {
                        totalCount++
                    }
                }

                var s = ""
                (left until right).forEach {
                    s += ",${nums[it]}"
                    result += s
                }

                if (nums[left++] % p == 0) {
                    totalCount--
                }
            }

            return result.size
        }
    }

    expect {
        Solution().countDistinct(
            intArrayOf(1, 9, 8, 7, 19), 1, 6
        )
    }
}