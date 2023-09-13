package p24xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun minimumTotalDistance(robot: MutableList<Int>, factory: Array<IntArray>): Long {
            robot.sort()
            factory.sortWith(compareBy { it[0] })

            val dp = LongArray(robot.size + 1) { Long.MAX_VALUE }

            dp[0] = 0L
            var sum = 0L
            for (repairRobotCount in 1..factory[0][1]) {
                sum += (robot[repairRobotCount - 1] - factory[0][0]).absoluteValue

                dp[repairRobotCount] = sum
            }

            for (factoryIndex in 1 until factory.size) {
                val (factoryPos, factoryCapacity) = factory[factoryIndex]
                for (totalRepairRobotCount in robot.size downTo 1) {
                    var distance = 0L
                    for (repairRobotCount in 1..factoryCapacity.coerceAtMost(totalRepairRobotCount)) {
                        val lastCost = dp[totalRepairRobotCount - repairRobotCount]

                        distance += (robot[totalRepairRobotCount - repairRobotCount] - factoryPos).absoluteValue

                        if (lastCost == Long.MAX_VALUE) {
                            continue
                        }

                        dp[totalRepairRobotCount] = dp[totalRepairRobotCount].coerceAtMost(lastCost + distance)
                    }
                }
            }

            return dp[robot.size]
        }
    }

    expect {
        Solution().minimumTotalDistance(
            mutableListOf(
                16076907,
                714823749,
                -464152749,
                -649061919,
                -438062380,
                -214791837,
                176249438,
                -909296023,
                908475766,
                -6625924,
                -584503977,
                -678071492,
                -829858512,
                16738758,
                22782758,
                -652048119,
                -617549349,
                80885411,
                773548451
            ), arrayOf(
                intArrayOf(383096391, 14),
                intArrayOf(-89339965, 8),
                intArrayOf(658903527, 2),
                intArrayOf(892591507, 10),
                intArrayOf(39847542, 15),
                intArrayOf(863337497, 15),
                intArrayOf(-635557012, 3)
            )
        )
    }
}