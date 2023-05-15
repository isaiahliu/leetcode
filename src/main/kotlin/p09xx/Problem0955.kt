package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDeletionSize(strs: Array<String>): Int {
            var result = 0

            val indices = (0 until strs.lastIndex).toMutableSet()

            var pos = 0
            loop@ while (pos < strs[0].length && indices.isNotEmpty()) {
                val processedIndices = hashSetOf<Int>()
                for (i in indices) {
                    val l = strs[i][pos]
                    val r = strs[i + 1][pos]
                    if (l > r) {
                        result++
                        pos++
                        continue@loop
                    } else if (l < r) {
                        processedIndices.add(i)
                    }
                }
                pos++
                indices.removeAll(processedIndices)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minDeletionSize(
            arrayOf("abx", "agz", "bgc", "bfc")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
