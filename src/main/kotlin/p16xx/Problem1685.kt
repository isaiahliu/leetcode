package p16xx

import util.expect

fun main() {
    class Solution {
        fun getSumAbsoluteDifferences(nums: IntArray): IntArray {
            var rightSum = nums.sum()
            var leftSum = 0

            return nums.mapIndexed { index, num ->
                rightSum -= num

                leftSum += num

                num + num * index - leftSum + rightSum - (nums.lastIndex - index) * num
            }.toIntArray()
        }
    }

    expect {
        Solution().getSumAbsoluteDifferences(
            intArrayOf()
        )
    }
}

