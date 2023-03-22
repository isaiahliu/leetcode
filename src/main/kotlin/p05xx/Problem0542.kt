package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
            val allFields = hashMapOf<Pair<Int, Int>, Int>()

            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num == 0) {
                        allFields[r to c] = 0
                    }
                }
            }

            val tasks = allFields.keys.toMutableSet()
            var distance = 0
            while (tasks.isNotEmpty()) {
                distance++
                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        it.first in mat.indices && it.second in mat[0].indices && it !in allFields
                    }.forEach {
                        tasks.add(it)
                        allFields[it] = distance
                    }
                }
            }

            return Array(mat.size) { r ->
                IntArray(mat[r].size) { c ->
                    allFields[r to c] ?: 0
                }
            }
        }
    }

    measureTimeMillis {
        Solution().updateMatrix(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 1, 0),
                intArrayOf(1, 1, 1)
            )
        ).map { it.toList() }.also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}