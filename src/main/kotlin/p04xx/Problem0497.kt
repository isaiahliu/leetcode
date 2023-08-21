package p04xx

import kotlin.random.Random
import kotlin.random.nextLong
import util.expect

fun main() {
    class Solution(val rects: Array<IntArray>) {
        val sizes = LongArray(rects.size) {
            val (x1, y1, x2, y2) = rects[it].map { it.toLong() }

            (x2 - x1 + 1) * (y2 - y1 + 1)
        }

        val sizeRange = 0 until sizes.sum()

        fun pick(): IntArray {
            var size = Random.nextLong(sizeRange)

            sizes.forEachIndexed { index, s ->
                if (size >= s) {
                    size -= s
                } else {
                    val (x1, y1, x2, y2) = rects[index]

                    val xRange = x2 - x1 + 1

                    val deltaY = size / xRange
                    val deltaX = size % xRange

                    return intArrayOf((x1 + deltaX).toInt(), (y1 + deltaY).toInt())
                }
            }

            return intArrayOf()
        }
    }

    expect {
        val s = Solution(
            arrayOf(
                intArrayOf(1, 1, 2, 2),
                intArrayOf(3, 3, 4, 4),
                intArrayOf(5, 5, 6, 6)
            )
        )

        val map = hashMapOf<Pair<Int, Int>, Int>()
        repeat(100) {
            s.pick().also { (x, y) ->
                map[x to y] = (map[x to y] ?: 0) + 1
            }
        }

        map
    }
}