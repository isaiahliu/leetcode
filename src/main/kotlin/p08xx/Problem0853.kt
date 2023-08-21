package p08xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
            val map = TreeMap<Int, Int>()

            position.forEachIndexed { index, p ->
                map[p] = speed[index]
            }

            var result = speed.size
            while (map.isNotEmpty()) {
                var current = target
                var finishedHour = -1.0

                while (true) {
                    val (p, s) = map.lowerEntry(current) ?: break

                    map.remove(p)

                    val nextP = p + s
                    when {
                        map.higherKey(p)?.takeIf { it <= nextP } != null -> {
                            result--
                        }

                        nextP >= target -> {
                            val hour = (target - p).toDouble() / s
                            if (hour > finishedHour) {
                                finishedHour = hour
                            } else {
                                result--
                            }
                        }

                        else -> {
                            map[nextP] = s
                        }
                    }

                    current = p
                }
            }

            return result
        }
    }

    expect {
        Solution().carFleet(
            31,
            intArrayOf(5, 26, 18, 25, 29, 21, 22, 12, 19, 6),
            intArrayOf(7, 6, 6, 4, 3, 4, 9, 7, 6, 4),
        )
    }
}