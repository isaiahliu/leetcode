package p32xx

import util.expect

fun main() {
    class Solution {
        fun resultsArray(nums: IntArray, k: Int): IntArray {
            var size = 0

            var last = nums[0]

            repeat(k - 1) {
                if (nums[it] != last + 1) {
                    size = 0
                }
                size++
                last = nums[it]
            }

            return IntArray(nums.size - k + 1) {
                if (nums[it + k - 1] != last + 1) {
                    size = 0
                }

                size++
                last = nums[it + k - 1]

                if (size >= k) last else -1
            }
        }
    }

    expect {
        Solution().resultsArray(
            intArrayOf(), 5
        )
    }
}
