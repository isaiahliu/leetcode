package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isRectangleCover(rectangles: Array<IntArray>): Boolean {
            fun IntArray.square(): Int {
                val (left, bottom, right, top) = this

                return (right - left) * (top - bottom)
            }

            fun IntArray.cover(target: IntArray): Boolean {
                val (left, bottom, right, top) = this
                val (targetLeft, targetBottom, targetRight, targetTop) = target

                return left < targetRight && targetLeft < right && bottom < targetTop && targetBottom < top
            }

            var (minLeft, minBottom, maxRight, maxTop) = rectangles[0]

            var sumSquares = 0

            val walked = arrayListOf<IntArray>()

            for (rectangle in rectangles) {
                if (walked.any { it.cover(rectangle) }) {
                    return false
                }

                walked.add(rectangle)

                sumSquares += rectangle.square()

                val (left, bottom, right, top) = rectangle
                minLeft = minLeft.coerceAtMost(left)
                minBottom = minBottom.coerceAtMost(bottom)
                maxRight = maxRight.coerceAtLeast(right)
                maxTop = maxTop.coerceAtLeast(top)
            }

            return (maxTop - minBottom) * (maxRight - minLeft) == sumSquares
        }
    }

    measureTimeMillis {
        Solution().isRectangleCover(
            arrayOf(
                intArrayOf(1, 1, 3, 3),
                intArrayOf(3, 1, 4, 2),
                intArrayOf(3, 2, 4, 4),
                intArrayOf(1, 3, 2, 4),
                intArrayOf(2, 3, 3, 4)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

