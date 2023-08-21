package p17xx

import util.expect

fun main() {
    class Solution {
        fun countGoodRectangles(rectangles: Array<IntArray>): Int {
            var result = 0
            var max = 0

            rectangles.forEach { (w, h) ->
                w.coerceAtMost(h).also {
                    if (it > max) {
                        max = it
                        result = 0
                    }

                    if (it == max) {
                        result++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countGoodRectangles(
            arrayOf()
        )
    }
}
