package p04xx

import util.expect

fun main() {
    class Solution {
        fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
            val result = arrayListOf<List<Int>>()

            val rain = Array(heights.size) { IntArray(heights[it].size) }

            val pacificSide = hashSetOf<Pair<Int, Int>>()
            val atlanticSide = hashSetOf<Pair<Int, Int>>()

            heights.indices.forEach {
                pacificSide.add(it to 0)
                atlanticSide.add(it to heights[it].lastIndex)
            }

            heights[0].indices.forEach {
                pacificSide.add(0 to it)
                atlanticSide.add(heights.lastIndex to it)
            }

            fun walk(set: Set<Pair<Int, Int>>, flag: Int) {
                val tasks = set.toMutableSet()

                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                        if (rain[r][c] and flag == 0) {
                            rain[r][c] += flag

                            if (rain[r][c] == 3) {
                                result.add(listOf(r, c))
                            }

                            arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).forEach { (nr, nc) ->
                                heights.getOrNull(nr)?.getOrNull(nc)?.also {
                                    if (it >= heights[r][c]) {
                                        tasks.add(nr to nc)
                                    }
                                }
                            }

                        }
                    }
                }
            }

            walk(pacificSide, 1)
            walk(atlanticSide, 2)

            return result
        }
    }

    expect {
        Solution().pacificAtlantic(
            arrayOf()
        )
    }
}


