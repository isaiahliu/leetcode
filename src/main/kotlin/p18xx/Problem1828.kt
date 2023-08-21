package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun countPoints(points: Array<IntArray>, queries: Array<IntArray>): IntArray {
            val map = TreeMap<Int, MutableMap<Int, Int>>()
            points.forEach { (x, y) ->
                map.computeIfAbsent(x) { hashMapOf() }.also {
                    it[y] = (it[y] ?: 0) + 1
                }
            }

            return queries.map { (x, y, r) ->
                var result = 0
                map.subMap(x - r, true, x + r, true).forEach { x1, ys ->
                    ys.forEach { (y1, count) ->
                        if ((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y) <= r * r) {
                            result += count
                        }
                    }
                }

                result
            }.toIntArray()
        }
    }

    expect {
        Solution().countPoints(
            arrayOf(), arrayOf()
        )

    }
}
