package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findReplaceString(s: String, indices: IntArray, sources: Array<String>, targets: Array<String>): String {
            var strIndex = 0
            var idxIndex = 0

            val dics =
                indices.mapIndexed { index, i -> i to (sources[index] to targets[index]) }.sortedBy { it.first }

            val result = StringBuilder()
            while (strIndex < s.length && idxIndex < dics.size) {
                val (i, p) = dics[idxIndex]
                if (strIndex == i) {
                    val (source, target) = p
                    if (source == s.substring(strIndex until (strIndex + source.length).coerceAtMost(s.length))) {
                        result.append(target)
                        strIndex += source.length
                    } else {
                        result.append(s[strIndex++])
                    }

                    idxIndex++
                } else {
                    result.append(s[strIndex++])
                }
            }

            result.append(s.substring(strIndex))

            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().findReplaceString(
            "", intArrayOf(), arrayOf(), arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}