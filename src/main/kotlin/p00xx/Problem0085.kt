package p00xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maximalRectangle(matrix: Array<CharArray>): Int {
            var max = 0

            val heights = IntArray(matrix[0].size)

            matrix.forEach {
                it.forEachIndexed { index, c ->
                    if (c == '0') {
                        heights[index] = 0
                    } else {
                        heights[index]++
                    }
                }

                max = max.coerceAtLeast(largestRectangleArea(heights))
            }

            return max
        }

        fun largestRectangleArea(heights: IntArray): Int {
            var max = 0

            val stack = LinkedList<IntArray>()

            fun popStack(lowerLimit: Int): Int {
                var count = 0
                while (stack.isNotEmpty() && stack.peek()[0] > lowerLimit) {
                    val top = stack.pop()
                    count += top[1]

                    max = max.coerceAtLeast(top[0] * count)
                }

                return count
            }
            heights.forEach {
                if (stack.isEmpty()) {
                    stack.push(intArrayOf(it, 1))
                } else {
                    val top = stack.peek()
                    when {
                        it < top[0] -> {
                            stack.push(intArrayOf(it, popStack(it) + 1))
                        }

                        it > top[0] -> {
                            stack.push(intArrayOf(it, 1))
                        }

                        else -> {
                            top[1]++
                        }
                    }
                }
            }

            popStack(-1)

            return max
        }
    }

    expect {
        Solution().maximalRectangle(emptyArray())
    }
}

