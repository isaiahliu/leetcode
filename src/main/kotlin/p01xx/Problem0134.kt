package p01xx

import util.expect

fun main() {
    class Solution {
        fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
            var startIndex = 0

            while (startIndex < gas.size && gas[startIndex] - cost[startIndex] < 0) {
                startIndex++
            }

            var sum = 0
            var endIndex = startIndex
            while (startIndex < gas.size && endIndex - startIndex < gas.size) {
                sum += gas[endIndex % gas.size] - cost[endIndex % gas.size]

                if (sum < 0) {
                    while (startIndex < gas.size && sum < 0) {
                        sum -= gas[startIndex] - cost[startIndex]
                        startIndex++
                    }
                }
                endIndex++
            }

            return if (startIndex < gas.size) {
                startIndex
            } else {
                -1
            }
        }
    }

    expect {
        Solution().canCompleteCircuit(
            intArrayOf(5, 1, 2, 3, 4), intArrayOf(4, 4, 1, 5, 1)
        )
    }
}

