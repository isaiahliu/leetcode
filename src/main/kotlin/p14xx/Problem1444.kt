package p14xx

import util.expect

fun main() {
    class Solution {
        fun ways(pizza: Array<String>, k: Int): Int {
            val tr = IntArray(pizza.size)
            val tc = IntArray(pizza[0].length)

            val rowSum = Array(pizza.size) { IntArray(pizza[it].length) }
            val columnSum = Array(pizza.size) { IntArray(pizza[it].length) }

            for (r in pizza.lastIndex downTo 0) {
                for (c in pizza[r].lastIndex downTo 0) {
                    if (pizza[r][c] == 'A') {
                        tr[r]++
                        tc[c]++
                    }

                    rowSum[r][c] = tr[r]
                    columnSum[r][c] = tc[c]
                }
            }

            val m = 1000000007

            val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, Int>()

            fun dfs(row: Int, column: Int, person: Int): Int {
                return when (val cacheKey = row to column to person) {
                    in cache -> {
                        cache[cacheKey] ?: 0
                    }

                    else -> {
                        var result = 0L

                        if (person == 1) {
                            if ((row..pizza.lastIndex).any { rowSum[it][column] > 0 }) {
                                result++
                            }
                        } else {
                            var hasApple = false
                            (row until pizza.lastIndex).forEach {
                                hasApple = hasApple || rowSum[it][column] > 0

                                if (hasApple) {
                                    result += dfs(it + 1, column, person - 1)
                                    result %= m
                                }
                            }

                            hasApple = false
                            (column until pizza[0].lastIndex).forEach {
                                hasApple = hasApple || columnSum[row][it] > 0

                                if (hasApple) {
                                    result += dfs(row, it + 1, person - 1)
                                    result %= m
                                }
                            }
                        }

                        cache[cacheKey] = result.toInt()

                        result.toInt()
                    }
                }
            }

            return dfs(0, 0, k)
        }
    }

    expect {
        Solution().ways(
            arrayOf(
                ".A..A",
                "A.A..",
                "A.AA.",
                "AAAA.",
                "A.AA."
            ), 5
        )

    }
}

