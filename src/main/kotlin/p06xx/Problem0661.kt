package p06xx

import util.expect

fun main() {
    class Solution {
        fun imageSmoother(img: Array<IntArray>): Array<IntArray> {
            img.forEachIndexed { r, row ->
                row.forEachIndexed { c, _ ->
                    row[c] += row.getOrNull(c - 1) ?: 0
                }

                row.forEachIndexed { c, _ ->
                    row[c] += img.getOrNull(r - 1)?.getOrNull(c) ?: 0
                }
            }

            return Array(img.size) { r ->
                IntArray(img[r].size) { c ->
                    val sum = img[(r + 1).coerceAtMost(img.lastIndex)][(c + 1).coerceAtMost(img[0].lastIndex)] -
                            (img.getOrNull(r - 2)?.getOrNull((c + 1).coerceAtMost(img[0].lastIndex)) ?: 0) -
                            (img.getOrNull((r + 1).coerceAtMost(img.lastIndex))?.getOrNull(c - 2) ?: 0) +
                            (img.getOrNull(r - 2)?.getOrNull(c - 2) ?: 0)

                    var rCount = 3
                    if (r == 0) {
                        rCount--
                    }

                    if (r == img.lastIndex) {
                        rCount--
                    }

                    var cCount = 3
                    if (c == 0) {
                        cCount--
                    }

                    if (c == img[0].lastIndex) {
                        cCount--
                    }

                    sum / (rCount * cCount)
                }
            }
        }
    }

    expect {
        Solution().imageSmoother(
            arrayOf(
                intArrayOf(100, 200, 100),
                intArrayOf(200, 50, 200),
                intArrayOf(100, 200, 100)
            )
        ).map { it.toList() }
    }
}