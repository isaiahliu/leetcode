package p23xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun buildMatrix(k: Int, rowConditions: Array<IntArray>, colConditions: Array<IntArray>): Array<IntArray> {
            fun Array<IntArray>.valid(): IntArray? {
                val result = IntArray(k + 1)
                val degrees = IntArray(k + 1)

                val parents = Array(k + 1) { hashSetOf<Int>() }
                forEach { (from, to) ->
                    if (parents[to].add(from)) {
                        degrees[from]++
                    }
                }

                val queue = LinkedList<Int>()

                queue.addAll((1 until degrees.size).filter { degrees[it] == 0 })

                var columnIndex = k - 1

                while (queue.isNotEmpty()) {
                    val r = queue.poll()

                    result[r] = columnIndex--

                    parents[r].forEach {
                        degrees[it]--
                        if (degrees[it] == 0) {
                            queue.add(it)
                        }
                    }
                }

                return result.takeIf { columnIndex == -1 }
            }

            val rows = rowConditions.valid() ?: return emptyArray()
            val columns = colConditions.valid() ?: return emptyArray()

            val result = Array(k) {
                IntArray(k)
            }

            (1..k).forEach {
                result[rows[it]][columns[it]] = it
            }

            return result
        }
    }

    expect {
        Solution().buildMatrix(
            8, arrayOf(
                intArrayOf(1, 2),
                intArrayOf(7, 3),
                intArrayOf(4, 3),
                intArrayOf(5, 8),
                intArrayOf(7, 8),
                intArrayOf(8, 2),
                intArrayOf(5, 8),
                intArrayOf(3, 2),
                intArrayOf(1, 3),
                intArrayOf(7, 6),
                intArrayOf(4, 3),
                intArrayOf(7, 4),
                intArrayOf(4, 8),
                intArrayOf(7, 3),
                intArrayOf(7, 5)
            ), arrayOf(
                intArrayOf(5, 7),
                intArrayOf(2, 7),
                intArrayOf(4, 3),
                intArrayOf(6, 7),
                intArrayOf(4, 3),
                intArrayOf(2, 3),
                intArrayOf(6, 2)
            )
        )
    }
}