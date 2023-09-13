package p24xx

import util.expect

fun main() {
    class Solution {
        fun maximumSubarraySum(nums: IntArray, k: Int): Long {
            var result = 0L

            var sum = 0L
            val pos = hashMapOf<Int, Int>()
            repeat(k) {
                sum += nums[it]
                pos[nums[it]] = it
            }

            if (pos.size == k) {
                result = sum
            }

            for (i in k until nums.size) {
                sum -= nums[i - k]
                pos[nums[i - k]].takeIf { it == i - k }?.also {
                    pos.remove(nums[i - k])
                }

                sum += nums[i]
                pos[nums[i]] = i

                if (pos.size == k) {
                    result = result.coerceAtLeast(sum)
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumSubarraySum(
            intArrayOf(1, 5, 4, 2, 9, 9, 9), 3
        )
    }
}