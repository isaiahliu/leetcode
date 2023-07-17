package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun diagonalSort(mat: Array<IntArray>): Array<IntArray> {
            val nodeMap = hashMapOf<Pair<Int, Int>, Int>()

            mat.indices.map { r ->
                mat[r].indices.map { c ->
                    r to c
                }
            }.flatten().groupBy { it.first - it.second }.values.forEach {
                val posSorted = it.sortedBy { it.first }
                val valueSorted = it.sortedBy { mat[it.first][it.second] }

                posSorted.forEachIndexed { index, pair ->
                    nodeMap[pair] = valueSorted[index].let { mat[it.first][it.second] }
                }
            }

            return Array(mat.size) { r ->
                IntArray(mat[r].size) { c ->
                    nodeMap[r to c] ?: 0
                }
            }
        }
    }

    measureTimeMillis {
        Solution().diagonalSort(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

