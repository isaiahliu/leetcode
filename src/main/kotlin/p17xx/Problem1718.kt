package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun constructDistancedSequence(n: Int): IntArray {
            fun dfs(index: Int, usedIndices: Set<Int>, route: List<Int>): IntArray? {
                if (route.size == n) {
                    val result = IntArray(n * 2 - 1)
                    var resultIndex = 0

                    route.forEach {
                        while (result[resultIndex] > 0) {
                            resultIndex++
                        }

                        if (it > 1) {
                            result[resultIndex + it] = it
                        }
                        result[resultIndex++] = it
                    }

                    return result
                }

                if (index in usedIndices) {
                    return dfs(index + 1, usedIndices, route)
                }

                for (num in n downTo 1) {
                    if (num !in route) {
                        if (num == 1 || index + num !in usedIndices && index + num < n * 2 - 1) {
                            var newUsed = usedIndices + index
                            if (num > 1) {
                                newUsed = newUsed + (index + num)
                            }
                            dfs(index + 1, newUsed, route + num)?.also {
                                return it
                            }
                        }
                    }
                }

                return null
            }

            return dfs(0, emptySet(), emptyList()) ?: intArrayOf()
        }
    }

    measureTimeMillis {
        Solution().constructDistancedSequence(
            20
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
