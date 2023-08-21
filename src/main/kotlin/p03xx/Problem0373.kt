package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
            val queue = PriorityQueue<List<Int>>(compareBy { nums1[it[0]] + nums2[it[1]] })

            nums1.indices.forEach { queue.offer(listOf(it, 0)) }

            val result = arrayListOf<List<Int>>()
            repeat(k) {
                if (queue.isEmpty()) {
                    return result
                }

                val min = queue.poll()
                result += min.let { listOf(nums1[it[0]], nums2[it[1]]) }
                (min[1] + 1).takeIf { it < nums2.size }?.also {
                    queue.add(listOf(min[0], it))
                }
            }

            return result
        }
    }

    expect {
        Solution().kSmallestPairs(
            intArrayOf(1, 7, 11), intArrayOf(2, 4, 6), 3
        )
    }
}

