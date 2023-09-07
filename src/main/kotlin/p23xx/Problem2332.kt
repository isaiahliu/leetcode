package p23xx

import util.expect

fun main() {
    class Solution {
        fun latestTimeCatchTheBus(buses: IntArray, passengers: IntArray, capacity: Int): Int {
            buses.sort()
            passengers.sort()

            val p = hashSetOf<Int>()
            passengers.forEach { p.add(it) }

            var busIndex = 0
            var count = 0
            var result = 0
            var passengerIndex = 0

            while (passengerIndex < passengers.size && busIndex < buses.size) {
                val passenger = passengers[passengerIndex]

                if (passenger <= buses[busIndex]) {
                    count++
                    passengerIndex++

                    if (passenger - 1 !in p) {
                        result = passenger - 1
                    }

                    if (count == capacity) {
                        count = 0
                        busIndex++
                    }
                } else {
                    if (buses[busIndex] !in p) {
                        result = buses[busIndex]
                    }
                    count = 0
                    busIndex++
                }
            }

            for (b in buses.lastIndex downTo busIndex) {
                if (buses[b] !in p) {
                    result = buses[b]
                    break
                }
            }

            return result
        }
    }

    expect {
        Solution().latestTimeCatchTheBus(
            intArrayOf(3, 4, 2), intArrayOf(2), 3
        )
    }
}