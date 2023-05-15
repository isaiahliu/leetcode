package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDeletionSize(strs: Array<String>): Int {
            var result = 0

            val comparison = IntArray(strs.size - 1) { 0 }

            loop@ for (pos in strs[0].indices) {
                val processedIndices = hashSetOf<Int>()
                var hasEqual = false
                for (i in 0 until strs.lastIndex) {
                    if (comparison[i] == 0) {
                        val l = strs[i][pos]
                        val r = strs[i + 1][pos]
                        if (l > r) {
                            result++
                            continue@loop
                        } else if (l == r) {
                            hasEqual = true
                        } else {
                            processedIndices.add(i)
                        }
                    }
                }

                if (!hasEqual) {
                    break
                } else {
                    processedIndices.forEach {
                        comparison[it] = 1
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minDeletionSize(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
