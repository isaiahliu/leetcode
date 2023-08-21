package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

            val indices = IntArray(matrix.size)

            matrix.forEachIndexed { index, row ->
                queue.add(row[0] to index)
            }

            var result = 0
            repeat(k) {
                val (num, index) = queue.poll()

                result = num

                indices[index]++

                matrix[index].getOrNull(indices[index])?.also {
                    queue.offer(it to index)
                }
            }

            return result
        }
    }

    expect {
        Solution().kthSmallest(
            arrayOf(), 4
        )
    }
}

