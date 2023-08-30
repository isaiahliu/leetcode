package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumFinishTime(tires: Array<IntArray>, changeTime: Int, numLaps: Int): Int {
            val wheels = TreeMap<Int, Int>()

            tires.forEach { (cost, tired) ->
                wheels.floorEntry(cost)?.takeIf { it.value <= tired }?.also {
                    return@forEach
                }

                while (true) {
                    wheels.ceilingEntry(cost)?.takeIf { it.value >= tired }?.also {
                        wheels.remove(it.key)
                    } ?: break
                }

                wheels[cost] = tired
            }

            var result = wheels.firstKey().toLong() * numLaps + changeTime.toLong() * (numLaps - 1)

            val dp = hashMapOf(0 to -changeTime.toLong())

            wheels.forEach { (cost, tired) ->
                var keepUsing = cost.toLong()
                val subDp = arrayListOf(keepUsing)
                var l = 2
                while (l <= numLaps) {
                    keepUsing = (cost + (keepUsing * tired)).coerceAtMost(result + 1)

                    var min = keepUsing
                    for (i in 0 until l / 2) {
                        min = min.coerceAtMost(subDp[i] + subDp[l - i - 2] + changeTime)
                    }

                    if (min < result) {
                        subDp.add(min)
                        l++
                    } else {
                        break
                    }
                }

                dp.toMap().forEach { (laps, time) ->
                    for ((newLaps, newTime) in subDp.withIndex()) {
                        if (laps + newLaps + 1 > numLaps) {
                            break
                        }

                        (time + changeTime + newTime).takeIf { it < result }?.also { newCost ->
                            dp[laps + newLaps + 1] = dp[laps + newLaps + 1]?.takeIf { it < newCost } ?: newCost
                        }
                    }
                }

                dp[numLaps]?.also {
                    result = result.coerceAtMost(it)
                }
            }

            return result.toInt()
        }
    }

    expect(318) {
        Solution().minimumFinishTime(
            arrayOf(
                intArrayOf(36, 5),
                intArrayOf(32, 5),
                intArrayOf(88, 8),
                intArrayOf(11, 4),
                intArrayOf(52, 2),
                intArrayOf(2, 2),
                intArrayOf(90, 5),
                intArrayOf(49, 6),
                intArrayOf(68, 9),
                intArrayOf(77, 3),
                intArrayOf(42, 7),
                intArrayOf(17, 3),
                intArrayOf(73, 7),
                intArrayOf(89, 2),
                intArrayOf(92, 9),
                intArrayOf(40, 7),
                intArrayOf(71, 8),
                intArrayOf(79, 3),
                intArrayOf(55, 6),
                intArrayOf(77, 9),
                intArrayOf(14, 3),
                intArrayOf(87, 10),
                intArrayOf(4, 2),
                intArrayOf(63, 7),
                intArrayOf(79, 8),
                intArrayOf(3, 9),
                intArrayOf(44, 2),
                intArrayOf(49, 9),
                intArrayOf(91, 3),
                intArrayOf(58, 6),
                intArrayOf(62, 3),
                intArrayOf(72, 7),
                intArrayOf(97, 6),
                intArrayOf(29, 5),
                intArrayOf(88, 9),
                intArrayOf(40, 8),
                intArrayOf(36, 4),
                intArrayOf(82, 8),
                intArrayOf(53, 8),
                intArrayOf(26, 2),
                intArrayOf(26, 6),
                intArrayOf(92, 2),
                intArrayOf(46, 2),
                intArrayOf(75, 6),
                intArrayOf(85, 2),
                intArrayOf(6, 10),
                intArrayOf(12, 4),
                intArrayOf(15, 4)
            ), 24, 27
        )
    }
}