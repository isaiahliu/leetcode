package p16xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().getSumAbsoluteDifferences(
            intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

