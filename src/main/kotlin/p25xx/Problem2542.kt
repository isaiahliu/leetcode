package p25xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
            val queue = PriorityQueue<Int>()
            var sum = 0L

            var result = 0L

            nums2.indices.sortedByDescending { nums2[it] }.forEach {
                val num2 = nums2[it]
                val num1 = nums1[it]

                queue.add(num1)
                sum += num1

                if (queue.size > k) {
                    sum -= queue.poll()
                }

                if (queue.size == k) {
                    result = maxOf(result, sum * num2)
                }
            }

            return result
        }
    }

    expect {
        Solution().maxScore(
            intArrayOf(1, 3, 3, 2), intArrayOf(2, 1, 3, 4), 3
        )
    }
}

