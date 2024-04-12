package p29xx

import util.expect

fun main() {
    class Solution {
        fun findChampion(grid: Array<IntArray>): Int {
            return grid[0].indices.first { team ->
                grid.indices.all {
                    grid[it][team] < 1
                }
            }
        }
    }

    expect {
        Solution().findChampion(
            arrayOf()
        )
    }
}
