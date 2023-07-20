package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMaxValueOfEquation(points: Array<IntArray>, k: Int): Int {
            val queue = LinkedList<Pair<Int, Int>>()

            var result = Int.MIN_VALUE
            points.forEach { (x, y) ->
                while (true) {
                    val first = queue.peek()?.first ?: break

                    if (x - first > k) {
                        queue.pollFirst()
                    } else {
                        break
                    }
                }

                queue.peek()?.also { (fx, fy) ->
                    result = result.coerceAtLeast(x + y - fx + fy)
                }

                while (true) {
                    val (lx, ly) = queue.peekLast() ?: break

                    if (y - ly >= x - lx) {
                        queue.pollLast() ?: break
                    } else {
                        break
                    }
                }

                queue.add(x to y)
            }

            return result
        }
    }
    measureTimeMillis {
        Solution().findMaxValueOfEquation(
            arrayOf(
                intArrayOf(-19, -12),
                intArrayOf(-13, -18),
                intArrayOf(-12, 18),
                intArrayOf(-11, -8),
                intArrayOf(-8, 2),
                intArrayOf(-7, 12),
                intArrayOf(-5, 16),
                intArrayOf(-3, 9),
                intArrayOf(1, -7),
                intArrayOf(5, -4),
                intArrayOf(6, -20),
                intArrayOf(10, 4),
                intArrayOf(16, 4),
                intArrayOf(19, -9),
                intArrayOf(20, 19)
            ), 6
        ).also { println(it) }
    }
}

