package p28xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumSumOfHeights(maxHeights: List<Int>): Long {
            val leftMax = LongArray(maxHeights.size)
            val rightMax = LongArray(maxHeights.size)

            val stack = LinkedList<Pair<Int, Long>>()
            var max = 0L

            for (i in maxHeights.indices) {
                val num = maxHeights[i]

                var size = 1L
                while (stack.isNotEmpty() && stack.peek().first >= num) {
                    val (n, c) = stack.poll()

                    max -= n * c
                    size += c
                }

                max += num * size
                stack.push(num to size)

                leftMax[i] = max
            }

            max = 0
            stack.clear()
            for (i in maxHeights.lastIndex downTo 0) {
                val num = maxHeights[i]

                var size = 1L
                while (stack.isNotEmpty() && stack.peek().first >= num) {
                    val (n, c) = stack.poll()

                    max -= n * c
                    size += c
                }

                max += num * size
                stack.push(num to size)

                rightMax[i] = max
            }

            return maxHeights.indices.maxOf {
                leftMax[it] + rightMax[it] - maxHeights[it]
            }
        }
    }

    expect {
        Solution().maximumSumOfHeights(
            listOf(5, 3, 4, 1, 1)
        )
    }
}
