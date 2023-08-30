package p22xx

import util.expect

fun main() {
    class Solution {
        fun digArtifacts(n: Int, artifacts: Array<IntArray>, dig: Array<IntArray>): Int {
            val degree = IntArray(artifacts.size)

            val grids = Array(n) {
                IntArray(n)
            }

            artifacts.forEachIndexed { index, (r1, c1, r2, c2) ->
                (r1..r2).forEach { r ->
                    (c1..c2).forEach { c ->
                        degree[index]++
                        grids[r][c] = index
                    }
                }
            }

            var result = 0

            dig.forEach { (r, c) ->
                if (--degree[grids[r][c]] == 0) {
                    result++
                }
            }
            return result
        }
    }

    expect {
        Solution().digArtifacts(
            1, arrayOf(), arrayOf()
        )
    }
}