package p30xx

import util.expect

fun main() {
    class Solution {
        fun largestSquareArea(bottomLeft: Array<IntArray>, topRight: Array<IntArray>): Long {
            val sortedIndices = bottomLeft.indices.sortedBy { bottomLeft[it][0] }

            var result = 0L
            for (i in sortedIndices.indices) {
                val (sx1, sy1) = bottomLeft[sortedIndices[i]]
                val (ex1, ey1) = topRight[sortedIndices[i]]

                for (j in i + 1 until sortedIndices.size) {
                    val (sx2, sy2) = bottomLeft[sortedIndices[j]]
                    val (ex2, ey2) = topRight[sortedIndices[j]]

                    if (sx2 >= ex1) {
                        break
                    }

                    minOf(minOf(minOf(ex1, ex2) - maxOf(sx1, sx2)), minOf(minOf(ey1, ey2) - maxOf(sy1, sy2))).takeIf { it > 0 }?.also {
                        result = maxOf(result, 1L * it * it)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().largestSquareArea(
            arrayOf(), arrayOf()
        )
    }
}
