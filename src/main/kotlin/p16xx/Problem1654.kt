package p16xx

import util.expect

fun main() {
    class Solution {
        fun minimumJumps(forbidden: IntArray, a: Int, b: Int, x: Int): Int {
            val FORWARD = 0
            val BACKWARD = 1

            val forbiddenSet = forbidden.toSet()
            val maxForbidden = forbidden.maxOrNull() ?: 0
            val visited = hashSetOf(0 to FORWARD)
            val tasks = hashSetOf(0 to FORWARD)

            var result = 0
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (pos, direction) ->
                    if (pos == x) {
                        return result
                    }

                    arrayOf(pos + a to FORWARD, pos - b to BACKWARD).forEach {
                        if ((it.second != BACKWARD || direction != BACKWARD) && it.first in 0..x.coerceAtLeast(
                                maxForbidden
                            ) + a + b && it.first !in forbiddenSet && visited.add(
                                it
                            )
                        ) {
                            tasks.add(it)
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    expect {
        Solution().minimumJumps(
            intArrayOf(
                162,
                118,
                178,
                152,
                167,
                100,
                40,
                74,
                199,
                186,
                26,
                73,
                200,
                127,
                30,
                124,
                193,
                84,
                184,
                36,
                103,
                149,
                153,
                9,
                54,
                154,
                133,
                95,
                45,
                198,
                79,
                157,
                64,
                122,
                59,
                71,
                48,
                177,
                82,
                35,
                14,
                176,
                16,
                108,
                111,
                6,
                168,
                31,
                134,
                164,
                136,
                72,
                98
            ),
            29,
            98,
            80,
        )
    }
}

