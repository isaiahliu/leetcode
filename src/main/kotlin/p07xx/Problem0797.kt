package p07xx

import util.expect

fun main() {
    class Solution {
        fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
            val cache = arrayOfNulls<List<List<Int>>>(graph.size)

            fun find(from: Int): List<List<Int>> {
                if (from == graph.lastIndex) {
                    return listOf(listOf(from))
                }

                cache[from]?.also { return it }

                val result = arrayListOf<List<Int>>()

                val fromNode = listOf(from)
                graph[from].forEach {
                    result.addAll(find(it).map {
                        fromNode + it
                    })
                }

                cache[from] = result

                return result
            }
            return find(0)
        }
    }

    expect {
        Solution().allPathsSourceTarget(
            arrayOf()
        )
    }
}