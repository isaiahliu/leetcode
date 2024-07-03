package p30xx

import util.expect

fun main() {
    class Solution {
        fun minimumMoves(nums: IntArray, k: Int, maxChanges: Int): Long {
            fun f(i: Int, nums: IntArray): Int {
                var x = nums[i]
                if (i - 1 >= 0) {
                    x += nums[i - 1]
                }
                if (i + 1 < nums.size) {
                    x += nums[i + 1]
                }
                return x
            }

            var left = 0
            var right = -1
            var leftSum = 0L
            var rightSum = 0L
            var leftCount = 0
            var rightCount = 0
            var result = Long.MAX_VALUE
            for (index in nums.indices) {
                if (f(index, nums) + maxChanges >= k) {
                    result = if (k <= f(index, nums)) {
                        minOf(result, (k - nums[index]).toLong())
                    } else {
                        minOf(result, (2L * k - f(index, nums) - nums[index]))
                    }
                }
                if (k <= maxChanges) {
                    continue
                }
                while (right + 1 < nums.size && (right - index < index - left || leftCount + rightCount + maxChanges < k)) {
                    if (nums[right + 1] == 1) {
                        rightCount++
                        rightSum += right + 1
                    }
                    right++
                }
                while (leftCount + rightCount + maxChanges > k) {
                    if (right - index < index - left || right - index == index - left && nums[left] == 1) {
                        if (nums[left] == 1) {
                            leftCount--
                            leftSum -= left
                        }
                        left++
                    } else {
                        if (nums[right] == 1) {
                            rightCount--
                            rightSum -= right
                        }
                        right--
                    }
                }
                result = minOf(
                    result,
                    (leftCount.toLong() * index - leftSum + rightSum - rightCount.toLong() * index + 2 * maxChanges)
                )
                if (nums[index] == 1) {
                    leftCount++
                    leftSum += index
                    rightCount--
                    rightSum -= index
                }
            }
            return result
        }
    }

    expect {
        Solution().minimumMoves(
            intArrayOf(1, 1, 0, 0, 0, 1, 1, 0, 0, 1), 3, 1
        )
    }
}
