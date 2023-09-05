package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun totalSteps(nums: IntArray): Int {
            var result = 0

            val queue = LinkedList<Pair<Int, Int>>()
            nums.forEach {
                var size = 0

                while (queue.isNotEmpty() && it >= queue.peek().first) {
                    size = size.coerceAtLeast(queue.poll().second)
                }

                size++

                if (queue.isNotEmpty()) {
                    result = result.coerceAtLeast(size)
                } else {
                    size = 1
                }

                queue.push(it to size)
            }

            return result
        }
    }

    expect(3) {
        Solution().totalSteps(
            intArrayOf(
                7, 1, 2, 1, 2
            )
        )
    }
}