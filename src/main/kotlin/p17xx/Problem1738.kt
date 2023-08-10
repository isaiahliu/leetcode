package p17xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kthLargestValue(matrix: Array<IntArray>, k: Int): Int {
            val queue = PriorityQueue<Int>()
            matrix.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    matrix[r].getOrNull(c - 1)?.also {
                        matrix[r][c] = num xor it
                    }
                }

                val lastRow = matrix.getOrNull(r - 1)
                row.forEachIndexed { c, num ->
                    lastRow?.get(c)?.also {
                        matrix[r][c] = num xor it
                    }

                    queue.offer(matrix[r][c])
                    if (queue.size > k) {
                        queue.poll()
                    }
                }
            }

            return queue.peek()
        }
    }

    measureTimeMillis {
        Solution().kthLargestValue(
            arrayOf(
                intArrayOf(5, 2),
                intArrayOf(1, 6)
            ), 1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
