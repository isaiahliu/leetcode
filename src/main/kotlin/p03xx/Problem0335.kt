package p03xx

import util.expect

fun main() {
    class Solution {
        fun isSelfCrossing(distance: IntArray): Boolean {
            var dec = false

            for (i in 2 until distance.size) {
                val opp = distance[i - 2]
                val cur = distance[i]

                if (dec) {
                    if (cur >= opp) {
                        return true
                    }
                } else {
                    if (cur <= opp) {
                        dec = true

                        val t = distance.getOrNull(i - 4) ?: 0

                        if (t + cur >= opp) {
                            distance[i - 1] -= distance.getOrNull(i - 3) ?: 0
                        }
                    }
                }
            }

            return false
        }
    }

    expect {
        Solution().isSelfCrossing(
            intArrayOf(1, 1, 1, 2, 1)
        )
    }
}

