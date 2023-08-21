package p17xx

import java.util.*
import util.expect

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
                        preSums.push(cost)

                        cost -= index - queue.poll() - queue.size
                    }
                }
            }

            repeat(k / 2) {
                preSums.poll()
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
                        preSums.poll()?.also {
                            result = result.coerceAtMost(cost + it)
                        } ?: break

                        cost -= queue.poll() - index - queue.size
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minMoves(
            intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1), 3
        )
    }
}
