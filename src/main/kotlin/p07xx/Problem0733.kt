package p07xx

import util.expect

fun main() {
    class Solution {
        fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
            if (image[sr][sc] == color) {
                return image
            }

            fun fillAt(r: Int, c: Int, cl: Int) {
                if (image.getOrNull(r)?.getOrNull(c) != cl) {
                    return
                }

                image[r][c] = color

                fillAt(r - 1, c, cl)
                fillAt(r + 1, c, cl)
                fillAt(r, c - 1, cl)
                fillAt(r, c + 1, cl)
            }

            fillAt(sr, sc, image[sr][sc])

            return image
        }
    }

    expect {
        Solution().floodFill(arrayOf(), 1, 1, 1)
    }
}