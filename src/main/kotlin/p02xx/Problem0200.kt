package p02xx

fun main() {
    class Solution {
        fun numIslands(grid: Array<CharArray>): Int {
            val lands = hashSetOf<Pair<Int, Int>>()

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, c ->
                    if (c == '1') {
                        lands.add(rowIndex to columnIndex)
                    }
                }
            }

            var result = 0

            fun removeLand(land: Pair<Int, Int>) {
                if (lands.remove(land)) {
                    removeLand(land.first - 1 to land.second)
                    removeLand(land.first + 1 to land.second)
                    removeLand(land.first to land.second - 1)
                    removeLand(land.first to land.second + 1)
                }
            }

            while (lands.isNotEmpty()) {
                result++

                removeLand(lands.first())
            }

            return result
        }
    }
    println(
        Solution().numIslands(
            emptyArray()
        )
    )
}

