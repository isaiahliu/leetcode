package p16xx

import util.expect

fun main() {
    class Solution {
        fun waysToMakeFair(nums: IntArray): Int {
            val forward = Array(nums.size + 1) { IntArray(2) }

            val sums = intArrayOf(0, 0)

            nums.forEachIndexed { index, num ->
                sums[index % 2] += num

                forward[index + 1][0] = sums[0]
                forward[index + 1][1] = sums[1]
            }

            val backward = Array(nums.size + 1) { IntArray(2) }
            sums[0] = 0
            sums[1] = 0

            var result = 0
            for (index in nums.lastIndex downTo 0) {
                val num = nums[index]

                if (forward[index][0] + backward[index + 1][1] == forward[index][1] + backward[index + 1][0]) {
                    result++
                }

                sums[index % 2] += num
                backward[index][0] = sums[0]
                backward[index][1] = sums[1]
            }

            return result
        }
    }

    expect {
        Solution().waysToMakeFair(
            intArrayOf(1, 2, 3)
        )
    }
}

