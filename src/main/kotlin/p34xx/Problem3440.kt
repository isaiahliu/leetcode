package p34xx

import util.expect

fun main() {
    class Solution {
        fun maxFreeTime(eventTime: Int, startTime: IntArray, endTime: IntArray): Int {
            var lastEndTime = 0

            val max = arrayOf(0 to 0, 0 to 0, 0 to 0)

            val distances = IntArray(startTime.size + 1) { index ->
                ((startTime.getOrNull(index) ?: eventTime) - lastEndTime).also {
                    if (it > max[0].second) {
                        max[2] = max[1]
                        max[1] = max[0]
                        max[0] = index to it
                    } else if (it > max[1].second) {
                        max[2] = max[1]
                        max[1] = index to it
                    } else if (it > max[2].second) {
                        max[2] = index to it
                    }

                    endTime.getOrNull(index)?.also {
                        lastEndTime = it
                    }
                }
            }

            return startTime.indices.maxOf { index ->
                val size = (endTime[index] - startTime[index]).takeIf {
                    max.any { (pos, l) ->
                        l >= it && pos != index && pos != index + 1
                    }
                } ?: 0

                distances[index] + distances[index + 1] + size
            }
        }
    }

    expect {
        Solution().maxFreeTime(
            34, intArrayOf(0, 17), intArrayOf(14, 19)
        )
    }
}
