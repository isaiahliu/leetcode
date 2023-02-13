package p00xx

import java.util.*

fun main() {
    class Solution {
        fun trap(height: IntArray): Int {
            val stack = LinkedList<Int>()
            var sum = 0

            height.forEachIndexed { index, h ->
                if (stack.isNotEmpty() && h >= height[stack.peek()]) {
                    var lastHeight = height[stack.pop()]
                    while (stack.isNotEmpty() && height[stack.peek()] <= h) {
                        val currentIndex = stack.pop()
                        val currentHeight = height[currentIndex]

                        sum += (index - currentIndex - 1) * (currentHeight.coerceAtMost(h) - lastHeight)

                        lastHeight = currentHeight
                    }

                    if (stack.isNotEmpty()) {
                        sum += (index - stack.peek() - 1) * (h - lastHeight)
                    }
                }
                stack.push(index)
            }

            return sum
        }
    }
    println(Solution().trap(intArrayOf(4, 2, 0, 3, 2, 5)))
}

