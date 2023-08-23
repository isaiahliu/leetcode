package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countHighestScoreNodes(parents: IntArray): Int {
            val totalSize = parents.size

            val degrees = IntArray(parents.size)

            val childrenSizes = Array(parents.size) {
                arrayListOf<Int>()
            }

            var max = -1L
            var result = 0

            parents.forEach { parent ->
                if (parent >= 0) {
                    degrees[parent]++
                }
            }

            val queue = LinkedList<Int>()

            degrees.forEachIndexed { index, degree ->
                if (degree == 0) {
                    queue.add(index)
                }
            }

            while (queue.isNotEmpty()) {
                val node = queue.poll()
                var size = 1
                var prod = 1L
                childrenSizes[node].forEach {
                    size += it
                    prod *= it
                }

                val parentSize = totalSize - size

                if (parentSize > 0) {
                    prod *= parentSize
                }

                if (prod > max) {
                    max = prod
                    result = 0
                }

                if (prod == max) {
                    result++
                }

                val parent = parents[node]

                if (parent >= 0) {
                    degrees[parent]--
                    if (degrees[parent] == 0) {
                        queue.add(parent)
                    }

                    childrenSizes[parent].add(size)
                }
            }

            return result
        }
    }

    expect {
        Solution().countHighestScoreNodes(
            intArrayOf(-1, 2, 0)
        )
    }
}