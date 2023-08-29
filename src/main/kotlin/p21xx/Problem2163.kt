package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumDifference(nums: IntArray): Long {
            val leftQueue = PriorityQueue<Int>(compareByDescending { it })
            val rightQueue = PriorityQueue<Int>()

            var leftSum = 0L
            var rightSum = 0L
            val size = nums.size / 3

            repeat(size) {
                leftSum += nums[it]
                rightSum += nums[nums.lastIndex - it]

                leftQueue.add(nums[it])
                rightQueue.add(nums[nums.lastIndex - it])
            }

            val rightList = LinkedList<Long>()
            rightList.push(rightSum)
            repeat(size) {
                rightSum += nums[nums.lastIndex - size - it]
                rightQueue.add(nums[nums.lastIndex - size - it])
                rightSum -= rightQueue.poll()

                rightList.push(rightSum)
            }

            var result = leftSum - rightList.poll()

            repeat(size) {
                leftSum += nums[size + it]
                leftQueue.add(nums[size + it])
                leftSum -= leftQueue.poll()


                result = result.coerceAtMost(leftSum - rightList.poll())
            }

            return result
        }
    }

    expect {
        Solution().minimumDifference(
            intArrayOf(7, 9, 5, 8, 1, 3)
        )
    }
}