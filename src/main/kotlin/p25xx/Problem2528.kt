package p25xx

import util.expect

fun main() {
    class Solution {
        fun maxPower(stations: IntArray, r: Int, k: Int): Long {
            val baseDiff = LongArray(stations.size)

            var sum = 0L

            stations.forEachIndexed { index, weight ->
                baseDiff[maxOf(index - r, 0)] += weight
                (index + r + 1).takeIf { it < baseDiff.size }?.also {
                    baseDiff[it] -= weight
                }

                sum += weight
            }
            var max = sum + k
            var min = 0L

            var result = min

            while (min <= max) {
                val mid = (min + max) / 2

                var success = true

                var remain = k.toLong()

                val offset = LongArray(stations.size)

                var sum = 0L
                for (index in stations.indices) {
                    sum += baseDiff[index]
                    sum += offset[index]

                    if (sum < mid) {
                        if (remain < mid - sum) {
                            success = false
                            break
                        }

                        remain -= mid - sum
                        (index + r * 2 + 1).takeIf { it < offset.size }?.also {
                            offset[it] -= mid - sum
                        }
                        sum = mid
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
        Solution().maxPower(
            intArrayOf(2, 10, 12, 3), 0, 14
        )
    }
}