package p09xx

import util.expect

fun main() {
    class Solution {
        fun tallestBillboard(rods: IntArray): Int {
            val map = hashMapOf(0 to 0)

            for (num in rods) {
                map.toMap().forEach { (key, value) ->
                    val l = key - num
                    val r = key + num

                    val score = value + num
                    if (map[l]?.takeIf { it > score } == null) {
                        map[l] = score
                    }
                    if (map[r]?.takeIf { it > score } == null) {
                        map[r] = score
                    }
                }
            }

            return (map[0] ?: 0) / 2
        }
    }

    expect {
        Solution().tallestBillboard(
            intArrayOf(1, 2, 3, 4, 5, 6)
        )
    }
}
