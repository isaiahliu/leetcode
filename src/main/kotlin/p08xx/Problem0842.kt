package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun splitIntoFibonacci(num: String): List<Int> {
            val visited = hashSetOf<Pair<Int, Int>>()
            fun dfs(startIndex: Int, route: List<Int>): List<Int>? {
                return when {
                    startIndex == num.length -> {
                        route.takeIf { it.size >= 3 }
                    }

                    route.size < 2 -> {
                        var n = 0
                        var index = startIndex

                        while (index < num.length) {
                            n *= 10
                            n += num[index] - '0'

                            if (n < 0) {
                                break
                            }

                            if (n == 0 && index > startIndex) {
                                break
                            }

                            dfs(++index, route + n)?.also {
                                return it
                            }

                            if (n == 0) {
                                break
                            }
                        }

                        null
                    }

                    else -> {
                        val targetNum = route.takeLast(2).sum().takeIf { it >= 0 } ?: return null

                        if (!visited.add(startIndex to targetNum)) {
                            return null
                        }

                        val targetNumStr = targetNum.toString()

                        if (num.startsWith(targetNumStr, startIndex)) {
                            dfs(startIndex + targetNumStr.length, route + targetNum)
                        } else {
                            null
                        }
                    }
                }
            }

            return dfs(0, emptyList()).orEmpty()
        }
    }

    measureTimeMillis {
        Solution().splitIntoFibonacci(
            "0000"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}