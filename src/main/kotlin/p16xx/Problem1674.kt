package p16xx

import util.expect

fun main() {
    class Solution {
        fun minMoves(nums: IntArray, limit: Int): Int {
            val diff = IntArray(limit * 2 + 2)

            repeat(nums.size / 2) {
                val left = nums[it].coerceAtMost(nums[nums.lastIndex - it])
                val right = nums[it].coerceAtLeast(nums[nums.lastIndex - it])

                diff[left + 1]--
                diff[left + right]--
                diff[left + right + 1]++
                diff[right + limit + 1]++
            }

            var result = Int.MAX_VALUE
            var sum = 0

            diff.forEach {
                sum += it
                result = result.coerceAtMost(nums.size + sum)
            }
            return result
        }
    }

    expect {
        Solution().minMoves(
            intArrayOf(1, 2, 4, 3), 4
        )
    }
}

