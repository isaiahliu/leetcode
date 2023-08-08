package p17xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minMoves(nums: IntArray, k: Int): Int {
            if (k == 1) {
                return 0
            }

            val preSums = LinkedList<Int>()

            val queue = LinkedList<Int>()
            var cost = 0
            for (index in nums.indices) {
                if (nums[index] == 0) {
                    cost += queue.size
                } else {
                    queue.add(index)

                    if (queue.size == (k + 1) / 2) {
                        preSums.add(cost)

                        cost -= index - queue.poll() - queue.size
                    }
                }
            }

            queue.clear()
            cost = 0

            val postSums = LinkedList<Int>()
            for (index in nums.lastIndex downTo 0) {
                if (nums[index] == 0) {
                    cost += queue.size
                } else {
                    queue.add(index)

                    if (queue.size == k / 2 + 1) {
                        postSums.push(cost)

                        cost -= queue.poll() - index - queue.size
                    }
                }
            }

            var result = Int.MAX_VALUE

            repeat((k - 1) / 2) {
                postSums.poll()
            }

            while (postSums.isNotEmpty()) {
                result = result.coerceAtMost(preSums.poll() + postSums.poll())
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().minMoves(
            intArrayOf(1, 1, 0, 1), 2
        ).also { println("${it} should be ${it}") }
    }.also { println("Time cost: ${it}ms") }
}
