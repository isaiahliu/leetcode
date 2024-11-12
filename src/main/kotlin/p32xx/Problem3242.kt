package p32xx

import util.expect

fun main() {
    class NeighborSum(private val grid: Array<IntArray>) {
        val pos = Array(grid.size * grid[0].size) { 0 to 0 }

        init {
            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    pos[num] = r to c
                }
            }
        }

        private val adj = arrayOf(0 to -1, 0 to 1, 1 to 0, -1 to 0)

        fun adjacentSum(value: Int): Int {
            return calc(value, adj)
        }

        private val diag = arrayOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1)

        fun diagonalSum(value: Int): Int {
            return calc(value, diag)
        }

        private fun calc(value: Int, p: Array<Pair<Int, Int>>): Int {
            val (r, c) = pos[value]

            return p.sumOf { (ro, co) ->
                grid.getOrNull(r + ro)?.getOrNull(c + co) ?: 0
            }
        }
    }

    expect {
        NeighborSum(arrayOf())
    }
}
