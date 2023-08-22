package p20xx

import util.expect

fun main() {
    class Solution {
        fun interchangeableRectangles(rectangles: Array<IntArray>): Long {
            fun Int.gcd(target: Int): Int {
                return if (this % target == 0) {
                    target
                } else {
                    target.gcd(this % target)
                }
            }

            val counts = hashMapOf<Pair<Int, Int>, Int>()

            rectangles.forEach { (w, h) ->
                val gcd = w.coerceAtLeast(h).gcd(w.coerceAtMost(h))

                val key = w / gcd to h / gcd
                counts[key] = (counts[key] ?: 0) + 1
            }

            var result = 0L

            counts.values.forEach {
                result += it.toLong() * (it - 1) / 2
            }

            return result
        }
    }

    expect {
        Solution().interchangeableRectangles(
            arrayOf(
                intArrayOf(4, 8),
                intArrayOf(3, 6),
                intArrayOf(10, 20),
                intArrayOf(15, 30),
            )
        )
    }
}