package p32xx

import util.expect

fun main() {
    class Solution {
        fun minFlips(grid: Array<IntArray>): Int {
            var result = 0
            var midSum = 0

            if (grid.size % 2 == 1) {
                repeat(grid[0].size / 2) { c ->
                    result += grid[grid.size / 2][c] xor grid[grid.size / 2][grid[0].lastIndex - c]

                    midSum += grid[grid.size / 2][c] + grid[grid.size / 2][grid[0].lastIndex - c]
                }
            }

            if (grid[0].size % 2 == 1) {
                repeat(grid.size / 2) { r ->
                    result += grid[r][grid[0].size / 2] xor grid[grid.lastIndex - r][grid[0].size / 2]

                    midSum += grid[r][grid[0].size / 2] + grid[grid.lastIndex - r][grid[0].size / 2]
                }
            }

            if (result == 0) {
                result += midSum % 4
            }

            if (grid.size % 2 == 1 && grid[0].size % 2 == 1) {
                result += grid[grid.size / 2][grid[0].size / 2]
            }

            repeat(grid.size / 2) { r ->
                repeat(grid[0].size / 2) { c ->
                    when (grid[r][c] + grid[r][grid[0].lastIndex - c] + grid[grid.lastIndex - r][c] + grid[grid.lastIndex - r][grid[0].lastIndex - c]) {
                        1, 3 -> result++
                        2 -> result += 2
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minFlips(arrayOf())
    }
}
