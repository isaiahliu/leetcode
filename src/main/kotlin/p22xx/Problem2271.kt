package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumWhiteTiles(tiles: Array<IntArray>, carpetLen: Int): Int {
            tiles.sortBy { it[0] }

            var l = 0
            var r = 1
            var gap = 0

            var result = 0
            while (l < tiles.size) {
                while (r < tiles.size && tiles[r][0] - tiles[l][0] < carpetLen) {
                    gap += tiles[r][0] - tiles[r - 1][1] - 1
                    r++
                }

                result =
                    result.coerceAtLeast(
                        carpetLen - (gap + (tiles[l][0] + carpetLen - tiles[r - 1][1] - 1).coerceAtLeast(
                            0
                        ))
                    )

                l++
                if (l == tiles.size) {
                    break
                }

                gap -= tiles[l][0] - tiles[l - 1][1] - 1
            }

            return result
        }
    }

    expect {
        Solution().maximumWhiteTiles(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(10, 11),
            ), 2
        )
    }
}