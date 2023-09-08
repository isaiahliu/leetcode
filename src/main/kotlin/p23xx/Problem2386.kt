package p23xx

import util.expect
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun kSum(nums: IntArray, k: Int): Long {
            val positiveSum = nums.fold(0L) { a, b ->
                if (b > 0) {
                    a + b
                } else {
                    a
                }
            }

            val queue = PriorityQueue<Long>(compareByDescending { it })
            queue.add(0L)

            for (num in nums.sortedBy { it.absoluteValue }) {
                if (queue.size == k && num.absoluteValue >= queue.peek()) {
                    break
                }

                queue.toList().forEach {
                    queue.add(it + num.absoluteValue)
                    if (queue.size > k) {
                        queue.poll()
                    }
                }
            }

            return positiveSum - queue.peek()
        }
    }

    expect {
        Solution().kSum(
            intArrayOf(1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009), 10
        )
    }
}