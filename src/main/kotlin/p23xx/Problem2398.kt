package p23xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumRobots(chargeTimes: IntArray, runningCosts: IntArray, budget: Long): Int {
            var min = 1
            var max = chargeTimes.size

            var result = 0

            while (min <= max) {
                val mid = min + (max - min) / 2

                var sum = 0L
                var success = false
                val chargingMap = TreeMap<Int, Int>()

                repeat(mid) {
                    sum += runningCosts[it]
                    chargingMap[chargeTimes[it]] = (chargingMap[chargeTimes[it]] ?: 0) + 1
                }

                if (chargingMap.lastKey() + sum * mid <= budget) {
                    success = true
                } else {
                    for (index in mid until chargeTimes.size) {
                        sum -= runningCosts[index - mid]
                        chargingMap[chargeTimes[index - mid]]?.also {
                            if (it == 1) {
                                chargingMap.remove(chargeTimes[index - mid])
                            } else {
                                chargingMap[chargeTimes[index - mid]] = it - 1
                            }
                        }

                        sum += runningCosts[index]
                        chargingMap[chargeTimes[index]] = (chargingMap[chargeTimes[index]] ?: 0) + 1

                        if (chargingMap.lastKey() + sum * mid <= budget) {
                            success = true
                            break
                        }
                    }
                }

                if (success) {
                    result = mid
                    min = mid + 1
                } else {
                    max = mid - 1
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumRobots(
            intArrayOf(8, 76, 74, 9, 75, 71, 71, 42, 15, 58, 88, 38, 56, 59, 10, 11),
            intArrayOf(1, 92, 41, 63, 22, 37, 37, 8, 68, 97, 39, 59, 45, 50, 29, 37),
            412
        )
    }
}