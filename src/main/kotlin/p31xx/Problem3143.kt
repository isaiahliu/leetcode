package p31xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maxPointsInsideSquare(points: Array<IntArray>, s: String): Int {
            val chars = hashSetOf<Char>()

            var result = 0
            s.indices.groupBy { points[it].let { (x, y) -> maxOf(x.absoluteValue, y.absoluteValue) } }
                .entries.sortedBy { it.key }.forEach { (length, points) ->
                    points.forEach {
                        if (!chars.add(s[it])) {
                            return result
                        }
                    }

                    result = chars.size
                }
            return result
        }
    }

    expect {
        Solution().maxPointsInsideSquare(
            arrayOf(), ""
        )
    }
}
