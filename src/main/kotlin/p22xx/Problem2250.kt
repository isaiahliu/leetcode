package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countRectangles(rectangles: Array<IntArray>, points: Array<IntArray>): IntArray {
            val rectCounts = TreeMap<Int, IntArray>(compareByDescending { it })

            rectangles.forEach { (x, y) ->
                rectCounts.computeIfAbsent(x) { IntArray(101) }[y]++
            }

            var pre: IntArray? = null

            rectCounts.forEach { (_, ys) ->
                for (y in ys.lastIndex - 1 downTo 0) {
                    ys[y] += ys[y + 1]
                }

                pre?.also { p ->
                    repeat(101) {
                        ys[it] += p[it]
                    }
                }

                pre = ys
            }

            return points.map { (r, c) ->
                rectCounts.floorEntry(r)?.value?.get(c) ?: 0
            }.toIntArray()
        }
    }

    expect {
        Solution().countRectangles(
            arrayOf(), arrayOf()
        )
    }
}