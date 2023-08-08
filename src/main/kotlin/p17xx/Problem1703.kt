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

            repeat(k / 2) {
                preSums.pollLast()
            }

            queue.clear()
            cost = 0

            var result = Int.MAX_VALUE
            for (index in nums.lastIndex downTo 0) {
                if (nums[index] == 0) {
                    cost += queue.size
                } else {
                    queue.add(index)

                    if (queue.size == k / 2 + 1) {
                        result = result.coerceAtMost(cost + preSums.pollLast())

                        if (preSums.isEmpty()) {
                            break
                        }
                        cost -= queue.poll() - index - queue.size
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minMoves(
            intArrayOf(1, 0, 0, 0, 0, 0, 1, 1), 3
        ).also { println("${it} should be ${it}") }
    }.also { println("Time cost: ${it}ms") }
}
